package com.hero.wireless.sender;

import com.drondea.sms.channel.ChannelSession;
import com.drondea.sms.common.util.CommonUtil;
import com.drondea.sms.message.ILongSMSMessage;
import com.drondea.sms.message.IMessage;
import com.drondea.sms.message.MessageProvider;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.Priority;
import com.hero.wireless.queue.FileQueue;
import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.util.QueueUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V3.0.0
 * @description: 提供发送包的接口
 * @author: 刘彦宁
 * @date: 2020年11月18日12:18
 **/
public class TcpMessageProvider implements MessageProvider {

    private Channel channel;
    private boolean isHoldStatus;

    public TcpMessageProvider(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
        if (SenderUtil.isChannelHold(channel)) {
            this.isHoldStatus = true;
        } else {
            this.isHoldStatus = false;
        }
    }

    @Override
    public List<IMessage> getTcpMessages(ChannelSession channelSession) {
        return pullMessageFromTopic(channelSession, channel.getNo());
    }

    @Override
    public void responseMessageMatchFailed(String requestKey, IMessage response) {
        //未匹配成功submit处理

    }

    /**
     * 返回是否存在消息,此处是立即返回，回调是异步执行
     *
     * @param channelNo           通道编号
     * @return
     */
    public List<IMessage> pullMessageFromTopic(ChannelSession channelSession, String channelNo) {
        //根据优先级获取
        for (Priority priority : Priority.values()) {
            FileQueue<Submit> queue = QueueUtil.getSubmitQueue(channelNo, priority);
            if (queue == null) {
                continue;
            }

            List<IMessage> requestMsg = new ArrayList<>(2);
            Submit submit = queue.poll();
            if (submit == null) {
                continue;
            }

            //2021.01.28 通道挂起状态，直接返回失败，消费掉mq
            if(submit != null && isHoldStatus()){
                SenderUtil.saveHoldMessage(submit, getChannel());
                continue;
            }

            //根据submit对象获取要提交的短信
            List<IMessage> longMsgToEntity = getSubmitMessage(channelSession, submit);
            if (longMsgToEntity == null) {
                continue;
            }
            requestMsg.addAll(longMsgToEntity);
            return requestMsg;
        }
        return null;
    }

    /**
     * 将submit转成协议需要的对象，并拆分长短信
     * @param channelSession
     * @param submit
     * @return
     */
    public List<IMessage> getSubmitMessage(ChannelSession channelSession, Submit submit) {
        //根据submit对象获取要提交的短信
        IMessage submitRequest = SenderUtil.getTcpSendMsg(getChannel(), submit, channelSession);
        if (submitRequest == null) {
            return null;
        }

        try {
            List<IMessage> longMsgToEntity = CommonUtil.getLongMsgSlices((ILongSMSMessage) submitRequest,
                    channelSession.getConfiguration(), channelSession.getSequenceNumber());
            return longMsgToEntity;
        } catch (Exception e) {
            SuperLogger.error("pullMessageFromTopic" , e);
        }
        return null;
    }


    public boolean isHoldStatus() {
        return this.isHoldStatus;
    }

}
