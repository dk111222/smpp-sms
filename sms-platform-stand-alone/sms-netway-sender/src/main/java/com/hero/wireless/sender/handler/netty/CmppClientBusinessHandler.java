package com.hero.wireless.sender.handler.netty;

import com.drondea.sms.channel.ChannelSession;
import com.drondea.sms.common.util.CommonUtil;
import com.drondea.sms.message.cmpp.CmppDeliverRequestMessage;
import com.drondea.sms.message.cmpp.CmppDeliverResponseMessage;
import com.drondea.wireless.util.CommonThreadPoolFactory;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.netty.handler.ReportWrap;
import com.hero.wireless.netty.sender.NettySender;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.Channel;
import com.hero.wireless.web.entity.send.Inbox;
import com.hero.wireless.web.util.QueueUtil;
import com.hero.wireless.web.util.SMSUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

/**
 * @version V3.0.0
 * @description: cmpp的客户端收到回执消息和MO业务处理
 * @author: 刘彦宁
 * @date: 2020年07月24日09:57
 **/
@ChannelHandler.Sharable
public class CmppClientBusinessHandler extends AbstractClientBusinessHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof CmppDeliverRequestMessage) {
            CmppDeliverRequestMessage dp = (CmppDeliverRequestMessage) msg;
            ChannelSession channelSession = CommonUtil.getChannelSession(ctx.channel());
            CommonThreadPoolFactory.getInstance().getBizPoolExecutor().submit(() -> {
                try {
                    // 状态报告
                    if (dp.isReport()) {
                        doReport(channelSession, dp);
                    } else {
                        if (dp.isMsgComplete()) {
                            doMo(channelSession, dp);
                        }
                    }
                } catch (Exception e) {
                    SuperLogger.error("deliver 出错了：" + e.getMessage(),e);
                }

            });
            responseDeliver(channelSession, dp);
            return;
        }
        super.channelRead(ctx, msg);
    }

    /**
     * 响应回执
     *
     * @param channelSession
     * @param dp
     */
    protected void responseDeliver(ChannelSession channelSession, CmppDeliverRequestMessage dp) {
        CmppDeliverResponseMessage responseMessage = new CmppDeliverResponseMessage(dp.getHeader());
        responseMessage.setResult(0);
        responseMessage.setMsgId(dp.getMsgId());
        NettySender.sendMessage(channelSession, responseMessage);
    }

    // 处理MO
    private void doMo(ChannelSession channelSession, CmppDeliverRequestMessage dp) {
        // 提取关键信息，其他数据json格式存储数据库
        SuperLogger.debug("*************CMPP接受的MO数据为：" + dp.toString());
        Inbox inbox = new Inbox();
        inbox.setChannel_No(channelSession.getConfiguration().getId());
        inbox.setContent(dp.getMsgContent());
        inbox.setSP_Number(dp.getDestId());
        inbox.setPhone_No(dp.getSrcTerminalId());
        inbox.setCreate_Date(new Date());
        inbox.setCharset(SMSUtil.getCharsetByByte(dp.getMsgFmt().getValue()));//编码
        // 发送mq消息
        QueueUtil.saveMo(inbox);
    }

    /**
     * 长短信也会有多条状态报告
     *
     * @param dp
     */
    private void doReport(ChannelSession channelSession, CmppDeliverRequestMessage dp) {
        String id = channelSession.getConfiguration().getId();
        Channel channel = DatabaseCache.getChannelCachedByNo(id);
        ReportWrap reportWrap = new ReportWrap(dp, channel);
        reportWrap.saveReport(5);
    }
}
