package com.hero.wireless.config;

import com.drondea.sms.session.AbstractClientSessionManager;
import com.drondea.sms.type.DefaultEventGroupFactory;
import com.drondea.wireless.util.IpUtil;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.ChannelStatus;
import com.hero.wireless.enums.Priority;
import com.hero.wireless.enums.ProtocolType;
import com.hero.wireless.queue.FileQueue;
import com.hero.wireless.sender.*;
import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.service.INetwayManage;
import com.hero.wireless.web.util.QueueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author volcano
 * @version V1.0
 * @date 2019年9月15日下午11:53:51
 */
@Component
public class NetwayEnv {


    /**
     * 通道对应的SessionManager
     */
    private final static Map<String, AbstractClientSessionManager> CHANNEL_SESSION_MANAGER = new ConcurrentHashMap<>();


    /**
     * 本地ip
     */
    public static String LOCAL_IP;

    @Resource(name = "cmppEnv")
    private CmppEnv cmppEnv;
    @Resource(name = "sgipEnv")
    private SgipEnv sgipEnv;
    @Resource(name = "smgpEnv")
    private SmgpEnv smgpEnv;
    @Resource(name = "smppEnv")
    private SmppEnv smppEnv;
    @Resource(name = "httpEnv")
    private HttpEnv httpEnv;

    @Autowired
    private INetwayManage netwayManage;

    public void start() {
        //检查通道创建连接
        startConnectionCheckTask();
    }

    /**
     * 定时检查一次所有连接，不足数目的就新建一个连接
     *
     * @author volcano
     * @date 2019年9月15日上午10:07:42
     * @version V1.0
     */
    private void startConnectionCheckTask() {
        //只筛选本发送器相关的channel
        Channel condition = new Channel();
        condition.setSender_Local_IP("127.0.0.1");
        //获取本地ip
        LOCAL_IP = IpUtil.getLocalIp();

        //5秒钟检测打开一次连接
        ScheduleServiceFactory.INS.submitUnlimitCircleTask(() -> {
            netwayManage.queryChannelList(condition).forEach(item -> {
                startChannel(item);
            });
            return true;
        }, 1000 * 3);

        //10秒钟检测关闭通道和切换发送器ip的通道
        ScheduleServiceFactory.INS.submitUnlimitCircleTask(() -> {
            CHANNEL_SESSION_MANAGER.keySet().stream().forEach((channelNo) -> {
                if (isChannelActive(channelNo)) {
                    return;
                }
                //关闭channel相关
                closeChannel(channelNo);
            });
            return true;
        }, 1000 * 10);
    }

    /**
     * 启动通道
     * @param channel
     */
    private void startChannel(Channel channel) {
        if(channel.getStatus_Code().equalsIgnoreCase(ChannelStatus.STOP.toString())){
            return; //停止状态
        }
        //没有设置ip和端口的放过
        if (channel.getIp() == null || channel.getPort() == null) {
            return;
        }

        //保存此channel的高中低的service
        List<FileQueue<Submit>> allMQServices = new ArrayList<>();
        //注册提交消费者
        for (Priority priority : Priority.values()) {
            FileQueue<Submit> submitFileQueue = QueueUtil.regSubmitQueue(channel.getNo(), priority);
            allMQServices.add(submitFileQueue);
        }

        //sgip有消息后再去创建连接
        if (ProtocolType.SGIP.equals(channel.getProtocol_Type_Code())) {
            //启动定时任务获取消息个数检测通道连接
            checkSgipConnection(channel, allMQServices);
        }

        //创建连接
        createSessionManager(channel);
    }

    /**
     * 定时检测是否有消息如果有创建连接
     * @param channel
     * @param mqServices
     */
    private void checkSgipConnection(Channel channel, List<FileQueue<Submit>> mqServices) {
        //遍历这个通道的高中低三个MQ看是否有消息，如果有消息就创建连接
        for (FileQueue<Submit> mqService:
                mqServices) {
            if (mqService == null) {
                return;
            }
            int cacheSize = mqService.size();
            createSgipConnection(channel, cacheSize);
        }
    }

    private boolean isChannelActive(String channelNo) {
        try {
            Channel condition = new Channel();
            condition.setNo(channelNo);
            List<Channel> channels = netwayManage.queryChannelList(condition);
            if(channels.size() > 0){
                return !channels.get(0).getStatus_Code().equalsIgnoreCase(ChannelStatus.STOP.toString());
            }
            return false;
        }catch (Exception e){
            SuperLogger.error(e.getMessage(),e);
            return false;
        }
    }

    private void closeChannel(String channelNo) {

        AbstractClientSessionManager sessionManager = CHANNEL_SESSION_MANAGER.remove(channelNo);
        if (sessionManager != null) {
            //关闭客户端
            sessionManager.doClose();
        }

        SuperLogger.debug("关闭监听" + channelNo);
    }

    private void createSessionManager(Channel channel) {
        AbstractClientSessionManager sessionManager = CHANNEL_SESSION_MANAGER.get(channel.getNo());
        if (sessionManager != null) {
            SenderUtil.resetChannelParam(sessionManager, channel);
            return;
        }
        //停止状态的不要创建连接了
        if (channel.getStatus_Code().equalsIgnoreCase(ChannelStatus.STOP.toString())) {
            return;
        }
        if (ProtocolType.CMPP.equals(channel.getProtocol_Type_Code())) {
            sessionManager = cmppEnv.createClient(channel, this);
        }

        if (ProtocolType.SMGP.equals(channel.getProtocol_Type_Code())) {
            sessionManager = smgpEnv.createClient(channel, this);
        }

        if (ProtocolType.SMPP.equals(channel.getProtocol_Type_Code())) {
            sessionManager = smppEnv.createClient(channel, this);
        }

        if (ProtocolType.HTTP.equals(channel.getProtocol_Type_Code())) {
            sessionManager = httpEnv.createClient(channel, this);
        }

        if (ProtocolType.SGIP.equals(channel.getProtocol_Type_Code())) {
            return;
        }

        CHANNEL_SESSION_MANAGER.put(channel.getNo(), sessionManager);
    }

    /**
     * 第一次创建sgip连接不管cacheSize，后续需要cacheSize>0即有数据才会创建连接
     * @param channel
     * @param cacheSize
     */
    private void createSgipConnection(Channel channel, int cacheSize) {

        String channelNo = channel.getNo();
        AbstractClientSessionManager sessionManager = CHANNEL_SESSION_MANAGER.get(channelNo);
        if (sessionManager != null) {
            //有消息才会再次连接
            if (cacheSize > 0) {
                sessionManager.doOpen();
            }
            return;
        }
        //加锁不要重复创建，需要再次检测是否已经创建
        synchronized (channel) {
            sessionManager = CHANNEL_SESSION_MANAGER.get(channelNo);
            //创建连接
            if (sessionManager != null) {
                return;
            }
            sessionManager = sgipEnv.createClient(channel, this);
            sessionManager.doOpen();
            CHANNEL_SESSION_MANAGER.put(channelNo, sessionManager);
        }

    }


    public static AbstractClientSessionManager getClientManager(String key) {
        return CHANNEL_SESSION_MANAGER.get(key);
    }


    @PreDestroy
    public void release() throws InterruptedException {

        SuperLogger.debug("关闭拉取程序");
        Thread.sleep(5 * 1000);
        SuperLogger.debug("关闭通道");
        //关闭所有的channel
        CHANNEL_SESSION_MANAGER.forEach((key, value) -> {
            value.doClose();
        });

        Thread.sleep(2 * 1000);
        DefaultEventGroupFactory.getInstance().close();
        Runtime.getRuntime().exit(0);
    }

}
