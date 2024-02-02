package com.hero.wireless.config;

import com.drondea.sms.conf.sgip.SgipClientSocketConfig;
import com.drondea.sms.session.sgip.SgipClientSessionManager;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.netway.SgipMessageProvider;
import com.hero.wireless.netway.handler.SgipClientCustomHandler;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.ext.EnterpriseUserExt;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * 联通区别于 移动 电信 ，上行和状态报告需要本地服务
 *
 * @author Lenovo
 */
@Component("sgipClientEnv")
public class SgipClientEnv {

    public SgipClientSessionManager createClient(EnterpriseUserExt enterpriseUser) {

        String userIdStr = String.valueOf(enterpriseUser.getId());
        Map<String, String> userProperties = DatabaseCache.getCachedUserProperties(userIdStr);
        //获取windowsize,设置推送MO和Report的窗口大小
        int windowSize = Integer.parseInt(StringUtils.defaultIfEmpty(userProperties.get("Window_Size"),"16"));

        //滑动窗口建议值为16
        SgipClientSocketConfig socketConfig = new SgipClientSocketConfig(userIdStr,
                10 * 1000, windowSize, enterpriseUser.getSgipSpIp(), Integer.valueOf(enterpriseUser.getSgipSpPort()));

        socketConfig.setCharset(Charset.forName("utf-8"));
        socketConfig.setGroupName("sgip");
        //LoginType:2  SMG向SP建立的连接，用于发送命令
        socketConfig.setLoginType((short) 2);
        socketConfig.setUserName(enterpriseUser.getTcp_User_Name());
        socketConfig.setPassword(enterpriseUser.getTcp_Password());
        socketConfig.setChannelSize(ObjectUtils.defaultIfNull(enterpriseUser.getTcp_Max_Channel(), 1));
        socketConfig.setCountersEnabled(true);
        socketConfig.setWindowMonitorInterval(10 * 1000);
        //设置响应超时时间
        socketConfig.setRequestExpiryTimeout(60 * 1000);
        //40秒没有消息就断开
        socketConfig.setIdleTime(40);

        SgipClientCustomHandler sgipCustomHandler = new SgipClientCustomHandler(enterpriseUser);

        SgipClientSessionManager sessionManager = new SgipClientSessionManager(socketConfig, sgipCustomHandler);

        //消息拉取的对象（新）
        sessionManager.setMessageProvider(new SgipMessageProvider(enterpriseUser));
        //创建链接
        sessionManager.doOpen();
        SuperLogger.debug("=======连接sgip用户=====" + enterpriseUser.getTcp_User_Name() + "成功=======");

        return sessionManager;
    }
}
