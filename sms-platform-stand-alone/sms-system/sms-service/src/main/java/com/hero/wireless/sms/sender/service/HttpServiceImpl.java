package com.hero.wireless.sms.sender.service;

import com.drondea.wireless.config.Constant;
import com.hero.wireless.http.AbstractHttp;
import com.hero.wireless.http.IHttpSendCallBack;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.util.ChannelUtil;
import com.hero.wireless.web.util.CodeUtil;
import com.hero.wireless.web.util.CopyUtil;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 接口业务
 * 
 * @author 张丽阳
 * 
 */
@Service("httpService")
public class HttpServiceImpl extends AbstractSenderService {

	@Resource(name = "httpSendCallBackService")
	IHttpSendCallBack httpSendCallBack;

	public void submit(Submit submit) throws Exception {
		Channel channel = DatabaseCache.getChannelCachedByNo(submit.getChannel_No());
		AbstractHttp abstractSubmit = (AbstractHttp) Class
				.forName(ChannelUtil.getParameter(channel, "full_class_impl", "")).newInstance();
		submit.setChannel_Msg_Id(CodeUtil.buildMsgNo());

		Integer maxSmsNumber = Integer.parseInt(ChannelUtil.getParameter(channel, "max_sms_number", "0"));
		String symbol = ChannelUtil.getParameter(channel, "mobile_split", ",");
		if (maxSmsNumber <= 0) {
			httpSubmit(submit, abstractSubmit);
		} else {
			List<String> phonesList = Arrays.asList(submit.getPhone_No().split(Constant.MUTL_MOBILE_SPLIT));
			ListUtils.partition(phonesList, maxSmsNumber).forEach(phones -> {
				try {
					String httpChannelMobiles = StringUtils.join(phones, symbol);
					Submit copySubmit = new Submit();
					CopyUtil.SUBMIT_COPIER.copy(submit, copySubmit, null);
					copySubmit.setPhone_No(httpChannelMobiles);
					httpSubmit(copySubmit, abstractSubmit);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
	}

	private void httpSubmit(Submit submit, AbstractHttp abstractSubmit) throws Exception {
		//提交时间
		submit.setSubmit_Date(new Date());
		submit.setCreate_Date(new Date());
		//异步提交，注册callback
		abstractSubmit.submit(submit, httpSendCallBack);
	}

}
