package com.hero.wireless.http.connector;

import com.drondea.wireless.util.SuperLogger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hero.wireless.enums.Charset;
import com.hero.wireless.enums.ReportStatus;
import com.hero.wireless.http.AbstractHttp;
import com.hero.wireless.http.IHttpSendCallBack;
import com.hero.wireless.json.JsonUtil;
import com.hero.wireless.notify.AliVoiceReport;
import com.hero.wireless.okhttp.AbstractCallback;
import com.hero.wireless.okhttp.CharsetResponseBody;
import com.hero.wireless.okhttp.HttpClient;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.send.Report;
import com.hero.wireless.web.entity.send.Submit;
import okhttp3.Call;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 阿里语音验证接口
 *
 * @author shd
 *
 */
public class AliVoiceImpl extends AbstractHttp {

	@Override
	public void report(Channel channel) {
	}

	@Override
	public Object report(ChannelReport channelReport) throws Exception {
		List<AliVoiceReport> reports = (List<AliVoiceReport>) channelReport.getData();
		reports.forEach(item -> {
			Report entity = new Report();
			entity.setChannel_No(item.getOut_id());
			entity.setChannel_Msg_Id(item.getCall_id());
			entity.setPhone_No(item.getCallee());
			String status = item.getStatus_code();
			entity.setNative_Status(status);

			if ("200000".equals(item.getStatus_code())) {
				entity.setStatus_Code(ReportStatus.SUCCESS.toString());
			} else {
				entity.setStatus_Code(ReportStatus.FAILD.toString());
			}
			entity.setStatus_Date(new Date());
			saveReport(entity);
		});
		return "success";
	}

	@Override
	public void submit(Submit submit, IHttpSendCallBack callBack) throws Exception {
		Channel channel = DatabaseCache.getChannelByNo(submit.getChannel_No());
		String url = submitUrl(channel);
		String templateCode = voiceTemplateCode(channel);
		String accessKeyId = channel.getAccount();
		String accessSecret = channel.getPassword();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		// 这里一定要设置GMT时区。
		df.setTimeZone(new SimpleTimeZone(0, "GMT"));
		Map<String, String> paras = new HashMap<>();
		// 1. 系统参数。
		paras.put("SignatureMethod", "HMAC-SHA1");
		paras.put("SignatureNonce", UUID.randomUUID().toString());
		paras.put("AccessKeyId", accessKeyId);
		paras.put("SignatureVersion", "1.0");
		paras.put("Timestamp", df.format(new Date()));
		paras.put("Format", "JSON");
		// 2. 业务API参数。
		paras.put("Action", "SingleCallByTts");
		paras.put("Version", "2017-05-25");
		paras.put("RegionId", "cn-hangzhou");
		paras.put("CalledNumber", submit.getPhone_No());
		// 如果要播报为一二三四，阿拉伯数字中间请用英文逗分隔开，写成1,2,3,4，如不隔开会变成一千二百三十四。目前暂定由下游处理
		paras.put("TtsParam", submit.getContent());
		paras.put("TtsCode", templateCode);
		String voiceSpeed = DatabaseCache.getStringValueBySystemEnvAndCode("voice_speed", "-500");
		paras.put("Speed", voiceSpeed); // 语速控制。取值范围为：-500~500
		paras.put("OutId", submit.getChannel_No());
		// 3. 去除签名关键字Key。
		if (paras.containsKey("Signature")) {
			paras.remove("Signature");
		}

		// 4. 参数KEY排序。
		TreeMap<String, String> sortParas = new TreeMap<>();
		sortParas.putAll(paras);
		// 5. 构造待签名的字符串。
		Iterator<String> it = sortParas.keySet().iterator();
		StringBuilder sortQueryStringTmp = new StringBuilder();
		while (it.hasNext()) {
			String key = it.next();
			sortQueryStringTmp.append("&").append(specialUrlEncode(key)).append("=").append(specialUrlEncode(paras.get(key)));
		}
		// 去除第一个多余的and（&）符号。
		String sortedQueryString = sortQueryStringTmp.substring(1);
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append("POST").append("&");
		stringToSign.append(specialUrlEncode("/")).append("&");
		stringToSign.append(specialUrlEncode(sortedQueryString));
		String sign = sign(accessSecret + "&", stringToSign.toString());
		// 6. 签名最后也要做特殊URL编码。
		String signature = specialUrlEncode(sign);
		paras.put("Signature", signature);
		HttpClient httpClient = new HttpClient();
		httpClient.setCharset(Charset.UTF8.toString());
		httpClient.postAsync(url, paras, new AbstractCallback(httpClient.getCharset()) {
			@Override
			public void ok(Call call, CharsetResponseBody response) throws Exception {
				String result = response.string();
				SuperLogger.debug(result);
				Map<String, String> resultMap = JsonUtil.STANDARD.readValue(result, new TypeReference<Map<String, String>>() {
				});

				String callId = resultMap.get("CallId");
				String code = resultMap.get("Code");
				submit.setChannel_Msg_Id(callId);
				submit.setSubmit_Description(resultMap.get("Message"));
				if ("OK".equals(code)) {
					callBack.success(submit);
					return;
				} else {
					callBack.failed(submit);
				}
			}

			@Override
			public void faild(Call call, IOException e) throws Exception {
				submit.setSubmit_Description("submit error");
				callBack.failed(submit);
			}
		});

	}

	@Override
	public String balance(Channel channel) {
		return null;
	}

	public static String specialUrlEncode(String value) throws Exception {
		return java.net.URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
	}
	public static String sign(String accessSecret, String stringToSign) throws Exception {
		javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
		mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));
		byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
		return new sun.misc.BASE64Encoder().encode(signData);
	}
	
	
	public static void main(String[] args) throws Exception {
		AliVoiceImpl voice = new AliVoiceImpl();
//		voice.submit(null);

//		String md5 = SecretUtil.MD5("8836BB8007ED9120002" + "alivoice" + "20190922091527561" + "6B5AF6041FE5607A495B8A41236A8A52");
//		System.out.println(md5);
	}
}
