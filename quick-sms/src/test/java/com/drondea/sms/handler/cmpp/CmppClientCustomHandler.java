package com.drondea.sms.handler.cmpp;
import com.drondea.sms.channel.ChannelSession;
import com.drondea.sms.common.SequenceNumber;
import com.drondea.sms.common.util.CommonUtil;
import com.drondea.sms.message.IMessage;
import com.drondea.sms.message.SendFailMessage;
import com.drondea.sms.message.cmpp.CmppSubmitRequestMessage;
import com.drondea.sms.session.AbstractClientSession;
import com.drondea.sms.thirdparty.SmsAlphabet;
import com.drondea.sms.thirdparty.SmsDcs;
import com.drondea.sms.thirdparty.SmsMessage;
import com.drondea.sms.thirdparty.SmsTextMessage;
import com.drondea.sms.type.DefaultEventGroupFactory;
import com.drondea.sms.type.ICustomHandler;
import com.drondea.sms.type.IMessageResponseHandler;
import com.drondea.sms.type.UserChannelConfig;
import com.drondea.sms.windowing.DuplicateKeyException;
import com.drondea.sms.windowing.OfferTimeoutException;
import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * @version V3.0.0
 * @description: cmpp的定制处理器
 * @author: 刘彦宁
 * @date: 2020年06月23日17:35
 **/
public class CmppClientCustomHandler extends ICustomHandler {
    private static final Logger logger = LoggerFactory.getLogger(CmppClientCustomHandler.class);
    @Override
    public void fireUserLogin(Channel channel, ChannelSession channelSession) {
        //注册session可写状态监听器
        channelSession.setSessionEventHandler((writable) -> {
            System.out.println("可写状态发生改变：" + writable);
        });
        final EventExecutor executor = channel.pipeline().firstContext().executor();
        System.out.println("用户登录成功--开始发送短信,端口号：" + ((AbstractClientSession)channelSession).getLocalPort());
        int i = 0;
        while (true) {
            if (i == 1) {
                break;
            }
            CmppSubmitRequestMessage requestMessage = new CmppSubmitRequestMessage();
            SequenceNumber sequenceNumber = channelSession.getSequenceNumber();
            requestMessage.getHeader().setSequenceId(sequenceNumber.next());
            String message = "【京东2】您的验证码:34590!";
//            String message = "【Hugging】code: 9237, used to " + Math.random();
            requestMessage.setMsgContent(message);
//            requestMessage.setMsgFmt(new SmsDcs((byte) 0));
//            SmsTextMessage smsMessage = (SmsTextMessage) requestMessage.getSmsMessage();
//            smsMessage.setText(message, new SmsDcs((byte) 0));
            requestMessage.setServiceId("1");
            requestMessage.setMsgSrc("AAAA");
            requestMessage.setSrcId("100590");
            requestMessage.setRegisteredDelivery((short) 1);
            requestMessage.setDestUsrTl((short) 1);
            requestMessage.setDestTerminalId(new String[]{"18010181663"});
            requestMessage.setSignature("【庄点科技】");
            //注册回调处理事件，收到响应的回调
            requestMessage.setMessageResponseHandler(new IMessageResponseHandler() {
                @Override
                public void messageComplete(IMessage request, IMessage response) {
                    CmppSubmitRequestMessage subReq = (CmppSubmitRequestMessage) request;
                }
                @Override
                public void messageExpired(String key, IMessage request) {
                    System.out.println("短信超时======" + request.getSequenceId());
                }
                @Override
                public void sendMessageFailed(IMessage request) {
                    System.out.println("send failure:" + request);
                }
            });
            int finalI = i;
            //切分长短信
            List<IMessage> longMsgSlices = CommonUtil.getLongMsgSlices(requestMessage, channelSession.getConfiguration(), sequenceNumber);
            longMsgSlices.forEach(msg -> {
                channelSession.sendMessage(msg);
            });
            i ++;
        }
    }
    @Override
    public void channelClosed(ChannelSession channelSession) {
        System.out.println("客户端断开连接");
    }
    @Override
    public void configPipelineAfterLogin(ChannelPipeline pipeline) {
        pipeline.addLast("CmppTestSubmitResponseHandler", new CmppTestSubmitResponseHandler());
        pipeline.addLast("CmppTestDeliveryRequestHandler", new CmppTestDeliveryRequestHandler());
    }
    @Override
    public void responseMessageExpired(Integer sequenceId, IMessage request) {
        System.out.println("短信超时处理" + sequenceId);
    }
    @Override
    public void slidingWindowException(ChannelSession session, ChannelHandlerContext ctx, IMessage message, ChannelPromise promise, Exception exception) {
        logger.error("slidingWindowException", exception);
        int retryCount = message.addRetryCount();
        //失败越多延时越长，防止线程堆积
        int delay = 10;
        if (retryCount >= 30) {
            delay = 500 * retryCount / 10;
        }
        if (retryCount > 20) {
            System.out.println("重试" + retryCount);
        }
        //重写
        ctx.executor().schedule(() -> {
            session.sendWindowMessage(ctx, message, promise);
        }, delay, TimeUnit.MILLISECONDS);
        //滑动窗口key冲突
        if (exception instanceof DuplicateKeyException) {
            return;
        }
        //滑动窗口获取slot超时
        if (exception instanceof OfferTimeoutException) {
            return;
        }
        if (exception instanceof InterruptedException) {
            return;
        }
    }
    @Override
    public boolean customLoginValidate(IMessage message, UserChannelConfig channelConfig, Channel channel) {
        return true;
    }
}
