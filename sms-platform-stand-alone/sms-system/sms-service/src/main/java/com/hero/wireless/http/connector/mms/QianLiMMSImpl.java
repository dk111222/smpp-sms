package com.hero.wireless.http.connector.mms;

import com.drondea.wireless.util.Base64Utils;
import com.drondea.wireless.util.SuperLogger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hero.wireless.enums.AuditStatus;
import com.hero.wireless.enums.Charset;
import com.hero.wireless.enums.MMSSubmitMaterialType;
import com.hero.wireless.enums.ReportStatus;
import com.hero.wireless.http.AbstractHttp;
import com.hero.wireless.http.IHttpSendCallBack;
import com.hero.wireless.json.JsonUtil;
import com.hero.wireless.okhttp.AbstractCallback;
import com.hero.wireless.okhttp.CharsetResponseBody;
import com.hero.wireless.okhttp.HttpClient;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.dao.business.IMmsMaterialDAO;
import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.business.MmsMaterial;
import com.hero.wireless.web.entity.business.MmsMaterialExample;
import com.hero.wireless.web.entity.business.MmsTemplate;
import com.hero.wireless.web.entity.send.Report;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.util.ApplicationContextUtil;
import okhttp3.Call;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* Title: QianLiImpl  
* Description:千立行业彩信（其实他们接的是迈远的接口），只支持图片 文字  不支持视频短信
* @author yjb
* @date 2019-12-25
 */
public class QianLiMMSImpl extends AbstractHttp {
	
	@Override
	public void report(Channel channel) {
		String url = reportUrl(channel);
		Map<String, String> params = new HashMap<String, String>();
		String[] userInfos = channel.getAccount().split(",");
		String account = userInfos[0];
		String id = userInfos.length > 1 ? userInfos[1] : "";
		params.put("userid", id);
		params.put("account", account);
		params.put("password", channel.getPassword());
		params.put("action", "query");
		HttpClient httpClient = new HttpClient();
		httpClient.setCharset(Charset.UTF8.toString());
		httpClient.postAsync(url, params, new AbstractCallback(httpClient.getCharset()) {
			@Override
			public void ok(Call call, CharsetResponseBody response) throws Exception {
				String resultXml = response.string();
				SuperLogger.debug(resultXml);
				if (StringUtils.isEmpty(resultXml)) {
					return;
				}
				Document resultDocument = DocumentHelper.parseText(resultXml);
				Element rootElement = resultDocument.getRootElement();
				List<?> reportElementList = rootElement.elements("statusbox");
				if (ObjectUtils.isEmpty(reportElementList)) {
					return;
				}
				reportElementList.forEach(item -> {
					Element report = (Element) item;
					Report entity = new Report();
					entity.setChannel_No(channel.getNo());
					String taskid = report.elementText("taskid");
					entity.setChannel_Msg_Id(taskid);
					entity.setPhone_No(report.elementText("mobile"));
					String status = report.elementText("status");
					entity.setNative_Status(status);
					if (status.trim().equals("10")) {
						entity.setStatus_Code(ReportStatus.SUCCESS.toString());
					} else {
						entity.setStatus_Code(ReportStatus.FAILD.toString());
					}
					entity.setRemark(report.elementText("errorcode"));
					saveReport(entity);
				});
			}
		});
	}

	@Override
	public void submit(Submit submit, IHttpSendCallBack callBack) throws Exception {
		Map<String, String> contMap= getQianLiMMSSendContent(submit.getContent(), submit.getEnterprise_No());
		Channel channel = DatabaseCache.getChannelByNo(submit.getChannel_No());
		String postURL = submitUrl(channel);
		HttpClient httpClient = new HttpClient();
		httpClient.setCharset(Charset.UTF8.toString());
		submit.setContent(setSignaturePre(submit.getContent()));
		Map<String, String> param = new HashMap<String, String>();
		String[] userInfos = channel.getAccount().split(",");
		String account = userInfos[0];
		String id = userInfos.length > 1 ? userInfos[1] : "";
		param.put("userid", id);
		param.put("account", account);
		param.put("password", channel.getPassword());
		param.put("mobile", submit.getPhone_No());
		param.put("title", contMap.get("title"));
		param.put("content", URLEncoder.encode(contMap.get("content"), "UTF-8"));
		param.put("action", "send");
		SuperLogger.debug("请求报文："+param);
		httpClient.postAsync(postURL, param, new AbstractCallback(httpClient.getCharset()) {

			@Override
			public void ok(Call call, CharsetResponseBody response) throws Exception {
				String info = response.string();
				SuperLogger.debug("返回报文："+info);
				if (StringUtils.isEmpty(info)) {
					submit.setSubmit_Description("response is blank");
					callBack.failed(submit);
					return;
				}
				if(!info.contains(":")){
					callBack.failed(submit);
					return;
				}
				String[] resultStr = info.split(":");
				String taskID = resultStr[1];
				submit.setChannel_Msg_Id(taskID);
				String description = String.format("余额：%1$s条,任务ID：%2$s", resultStr[0], resultStr[1]);
				submit.setSubmit_Description(description);
				callBack.success(submit);
			}

			@Override
			public void faild(Call call, IOException e) throws Exception {
				submit.setSubmit_Description("submit error");
				callBack.failed(submit);
			}
		});

	}

	/**
	 * 发送内容组装
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getQianLiMMSSendContent(String content, String enterpriseNo) throws Exception{
		Map<String, String> resultMap = new HashMap<String, String>();
		StringBuffer sendStr = new StringBuffer();
		// 播放时间
		sendStr.append("3").append(",");
		Map<String, Object> map = JsonUtil.STANDARD.readValue(content, new TypeReference<Map<String, Object>>() {});
		String templateCode = (String) map.get("templateCode");
		MmsTemplate mmsTemplate = DatabaseCache.getMmsTemplateCacheByCode(templateCode);
		Map<String, String> contentMap = JsonUtil.STANDARD.readValue(mmsTemplate.getTemplate_Content(), new TypeReference<Map<String, String>>() {});
		List<Map<String, String>> submitMap = JsonUtil.STANDARD.readValue(contentMap.get("data"), new TypeReference<List<Map<String, String>>>() {});
		resultMap.put("title", contentMap.get("title"));
		for(int i = 0; i < submitMap.size(); i++){
			String contentStr = submitMap.get(i).get("content");
			if(MMSSubmitMaterialType.TXT.toString().equalsIgnoreCase(submitMap.get(i).get("type"))){
				contentStr = Base64Utils.encode(contentStr.getBytes("gb2312"));
			}
			if(MMSSubmitMaterialType.PICTURE.toString().equalsIgnoreCase(submitMap.get(i).get("type"))){
				IMmsMaterialDAO<MmsMaterial> mmsMaterialDAO = ApplicationContextUtil.getBean("IMmsMaterialDAO");
				MmsMaterialExample example = new MmsMaterialExample();
				example.createCriteria().andEnterprise_NoEqualTo(enterpriseNo)
				.andMaterial_CodeEqualTo(contentStr).andApprove_StatusEqualTo(AuditStatus.PASS.value());
				List<MmsMaterial> list = mmsMaterialDAO.selectByExample(example);
				if(ObjectUtils.isEmpty(list)){
					throw new Exception("素材不存在");
				}
				submitMap.get(i).put("type", list.get(0).getFormat().toLowerCase());
				String realUrl =  list.get(0).getUrl();
				String pathPrefix = DatabaseCache.getStringValueBySystemEnvAndCode("file_path_prefix","/mnt");
				File jpgFile = new File(pathPrefix + realUrl);
				byte[] jpgBytes;
				InputStream jpgInputStream = null;
				try {
					jpgInputStream = new FileInputStream(jpgFile);
					jpgBytes = new byte[jpgInputStream.available()];
					jpgInputStream.read(jpgBytes);
				} catch (FileNotFoundException e) {
					SuperLogger.error(e);
					throw new Exception("获取素材异常");
				} catch (Exception e) {
					SuperLogger.error(e.getMessage());
					throw new Exception("获取素材异常");
				} finally {
					jpgInputStream.close();
				}
				contentStr = Base64Utils.encode(jpgBytes);
			}
			if(i == (submitMap.size()-1)){
				sendStr.append(submitMap.get(i).get("type")).append("|").append(contentStr);
			} else {
				sendStr.append(submitMap.get(i).get("type")).append("|").append(contentStr).append(",");
			}
		}
		resultMap.put("content", sendStr.toString());
		return resultMap;
	}

	@Override
	public String balance(Channel channel) throws Exception {
		String result = null;
		String url = balanceUrl(channel);
		String[] userInfos = channel.getAccount().split(",");
		String account = userInfos[0];
		String id = userInfos.length > 1 ? userInfos[1] : "";
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "overage");
		params.put("userid", id);
		params.put("account", account);
		params.put("password", channel.getPassword());
		HttpClient httpClient = new HttpClient();
		httpClient.setCharset(Charset.UTF8.toString());
		CharsetResponseBody response = httpClient.post(url, params);
		if (response == null) {
			return null;
		}
		String info = response.string();
		SuperLogger.debug(info);
		Document resultDocument = DocumentHelper.parseText(info);
		Element rootElement = resultDocument.getRootElement();
		if (ObjectUtils.isEmpty(rootElement)) {
			return null;
		}
		if(!rootElement.elementText("returnstatus").equalsIgnoreCase("success")){
			SuperLogger.debug("查询余额失败");
			return null;
		}
		result = String.format("余额：%1$s条,发送：%2$s条", rootElement.elementText("overage"),
				rootElement.elementText("sendTotal"));
		return result;
	}

	@Override
	public void mo(Channel channel) throws Exception {
		
	}
	
}
