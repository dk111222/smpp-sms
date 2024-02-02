package com.hero.wireless.config;

import com.drondea.sms.conf.sgip.SgipClientSocketConfig;
import com.drondea.sms.conf.sgip.SgipServerSocketConfig;
import com.drondea.sms.session.sgip.SgipClientSessionManager;
import com.drondea.sms.session.sgip.SgipServerSessionManager;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.ProtocolType;
import com.hero.wireless.netway.handler.SgipServerCustomHandler;
import com.hero.wireless.netway.service.ScheduleServiceFactory;
import com.hero.wireless.netway.service.TcpValidator;
import com.hero.wireless.queue.FileQueue;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.EnterpriseUser;
import com.hero.wireless.web.entity.business.ext.EnterpriseUserExt;
import com.hero.wireless.web.entity.send.Inbox;
import com.hero.wireless.web.entity.send.Report;
import com.hero.wireless.web.service.IEnterpriseManage;
import com.hero.wireless.web.service.IPropertyManage;
import com.hero.wireless.web.util.QueueUtil;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetector.Level;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * sgip网关
 *
 * @author lyh
 */
@Component
public class NetwaySgipServerEnv {
    public static final String SGIP_SERVER_ID = "sgip_server";
    private final String SGIP_CLIENT_DEFAULT_PORT = "8811";
    @Resource(name = "sgipClientEnv")
    private SgipClientEnv sgipClientEnv;


    public final static Map<String, SgipClientSessionManager> SESSION_MANAGER_INFOS = new ConcurrentHashMap<>();


    public SgipServerSessionManager start() {
        ResourceLeakDetector.setLevel(Level.ADVANCED);

        // 获取配置端口，为空获取SGIP默认端口
        int port = DatabaseCache.getIntValueBySortCodeAndCode("sgip_netway",
                "sgip_server_port", 8801);
        SgipServerSocketConfig socketConfig = new SgipServerSocketConfig(SGIP_SERVER_ID, port);
        //超时时间60秒
        socketConfig.setIdleTime(60);
        SgipServerCustomHandler customHandler = new SgipServerCustomHandler();

        SgipServerSessionManager sessionManager = new SgipServerSessionManager(new TcpValidator(ProtocolType.SGIP) {
            @Override
            public void startConsumer(EnterpriseUserExt user) {
                checkSgipConnection(user);
            }
        }, socketConfig, customHandler);

        sessionManager.doOpen();

        SuperLogger.debug("启动SGIP网关，端口:" + port);
        return sessionManager;
    }

    public EnterpriseUserExt getUserInfo(String userName) {
        EnterpriseUser userInfo = DatabaseCache.getUserByCachedTcpUserName(userName);
        if (userInfo == null) {
            return null;
        }

        return DatabaseCache.getSgipUserExtInfo(userInfo);
    }

    /**
     * 定时检测是否有消息如果有创建连接
     * @param user
     */
    private void checkSgipConnection(EnterpriseUserExt user) {

        ScheduleServiceFactory.INS.submitUnlimitCircleTask(() -> {
            int userId = user.getId();

            FileQueue<Report> reportQueue = QueueUtil.getReportQueue(ProtocolType.SGIP, userId);
            if (reportQueue != null) {
                //查看回执是否有消息
                int cacheSize = reportQueue.size();
                if (cacheSize > 0) {
                    createSgipConnection(user);
                }
            }

            FileQueue<Inbox> inboxFileQueue = QueueUtil.regInboxQueue(ProtocolType.SGIP, userId);
            if (inboxFileQueue != null) {
                //查看MO是否有消息
                int cacheSize = inboxFileQueue.size();
                if (cacheSize > 0) {
                    createSgipConnection(user);
                }
            }

            return true;
        },1000 * 3);

    }

    private void createSgipConnection(EnterpriseUserExt user) {
        String userName = user.getTcp_User_Name();
        SgipClientSessionManager sgipClientSessionManager =
                SESSION_MANAGER_INFOS.get(userName);

        //修改用户ip和端口的时候要热加载
        EnterpriseUserExt userInfo = getUserInfo(userName);

        //未配置下游连接的ip或者port
        if (StringUtils.isEmpty(userInfo.getSgipSpIp()) ) {
            SuperLogger.info(userName + ":sgip下游IP未配置");
            return;
        }

        if (userInfo != null) {
            user.setSgipSpIp(userInfo.getSgipSpIp());
            String sgipSpPort = StringUtils.isNotEmpty(userInfo.getSgipSpPort()) ? userInfo.getSgipSpPort() : SGIP_CLIENT_DEFAULT_PORT;
            user.setSgipSpPort(sgipSpPort);
        }

        //防止端口冲突
        if (sgipClientSessionManager != null) {
            SgipClientSocketConfig socketConfig = (SgipClientSocketConfig) sgipClientSessionManager.getSocketConfig();
            String host = socketConfig.getHost();
            int port = socketConfig.getPort();
            String password = socketConfig.getPassword();
            int oldWindowSize = socketConfig.getWindowSize();

            //修改密码的情况
            if (!password.equals(user.getTcp_Password())) {
                socketConfig.setPassword(user.getTcp_Password());
            }
            //修改ip或port要重启
            if (!host.equals(user.getSgipSpIp()) || port != Integer.parseInt(user.getSgipSpPort())) {
                socketConfig.setHost(user.getSgipSpIp());
                socketConfig.setPort(Integer.parseInt(user.getSgipSpPort()));
                sgipClientSessionManager.doClose();
                SuperLogger.debug("sgip修改连接下游信息：" + userName + ": " +
                        "host:" + user.getSgipSpIp() + " port:" + user.getSgipSpPort());
            }
            int windowSize = Integer.parseInt(userInfo.getWindowSize());
            //修改窗口大小
            if (oldWindowSize != windowSize) {
                sgipClientSessionManager.resetWindowSize(windowSize);
                SuperLogger.debug("sgip修改窗口：" + userName + ": " +
                        "windowSize:" + windowSize);
            }
            //看连接数是否改变，改变的话要修改
            int oldChannelSize = socketConfig.getChannelSize();
            Integer maxChannelSize = ObjectUtils.defaultIfNull(user.getTcp_Max_Channel(), 1);
            if (oldChannelSize != maxChannelSize) {
                socketConfig.setChannelSize(maxChannelSize);
            }
            SuperLogger.debug("sgip已经连接下游：" + userName + ": host:" + host + " " +
                    "port:" + port + " connection size:" + sgipClientSessionManager.getSessionSize());
            sgipClientSessionManager.doOpen();
            return;
        }

        if (!StringUtils.isNumeric(user.getSgipSpPort())) {
            SuperLogger.error(user.getUser_Name() + "配置的sgip端口号为：" + user.getSgipSpPort());
            return;
        }

        String key = "SGIP_CLIENT_" + userName;
        //加锁不要重复创建，需要再次检测是否已经创建
        synchronized (key.intern()) {
            sgipClientSessionManager = SESSION_MANAGER_INFOS.get(userName);
            //创建连接
            if (sgipClientSessionManager != null) {
                return;
            }

            sgipClientSessionManager = sgipClientEnv.createClient(user);
            SESSION_MANAGER_INFOS.put(userName, sgipClientSessionManager);
        }
    }

}