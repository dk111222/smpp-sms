package com.hero.wireless.timer;

import com.hero.wireless.enums.Priority;
import com.hero.wireless.sms.sender.service.ISenderSmsService;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.MobileAreaExample;
import com.hero.wireless.web.entity.send.Input;
import com.hero.wireless.web.entity.send.Report;
import com.hero.wireless.web.entity.send.ReportAwait;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.service.IBusinessManage;
import com.hero.wireless.web.util.CopyUtil;
import com.hero.wireless.web.util.QueueUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuyanning
 */
@Service
public class CacheLoaderScheduler {

    @Resource(name = "databaseCache")
    private DatabaseCache databaseCache;
    @Resource(name = "defaultSenderServiceImpl")
    private ISenderSmsService senderSmsService;
    @Resource(name = "businessManage")
    private IBusinessManage businessManage;

    /**
     * 手机归属地缓存条数
     */
    private static int mobileAreaCount = 0;

    @Scheduled(fixedDelay = 5 * 60 * 1000, initialDelay = 3 * 60 * 1000)
    public void update() {
        databaseCache.init();
        //加载敏感字
        DatabaseCache.refreshSensitiveWordLocalCache();
        //刷新导流策略
        DatabaseCache.refreshDiversionLocalCache();
        //加载号码路由
        DatabaseCache.refreshSmsRouteLocalCache();
        //加载白名单
        DatabaseCache.refreshWhiteListLocalCache();
        //加载黑名单
        DatabaseCache.refreshBlackListLocalCache();
        //加载通道签名、关键字
        DatabaseCache.refreshChannelDiversionLocalCache();
    }

    @Scheduled(fixedDelay = 3 * 60 * 60 * 1000, initialDelay = 10 * 1000)
    public void updateMobileArea() {
        int total = businessManage.queryMobileAreaCount(new MobileAreaExample());
        if(total != mobileAreaCount){
            mobileAreaCount = total;
            //加载手机号归属地
            DatabaseCache.refreshMobileAreaLocalCache();
        }
    }

    /**
     * 通道测试功能，将待发送表中的数据保存到本地队列中
     */
    @Scheduled(fixedDelay = 5 * 1000, initialDelay = 10 * 1000)
    public void loadSubmitWait() {
        //一直加载
        while (true) {
            List<Submit> submitAwait = senderSmsService.getSubmitAwaitAndDel(0L);
            if (ObjectUtils.isEmpty(submitAwait)) {
                break;
            }
            //将submit数据放入到本地队列
            submitAwait.forEach(submit -> {
                Priority priority = Priority.valueOf(submit.getPriority_Level());
                QueueUtil.sendSubmit2Queue(priority, submit);
            });
        }
    }

    /**
     * 将待处理的回执拉取
     */
    @Scheduled(fixedDelay = 5 * 1000, initialDelay = 10 * 1000)
    public void loadReportWait() {
        //一直加载
        while (true) {
            List<ReportAwait> reportAwaits = senderSmsService.getReportAwaitAndDel(0L);
            if (ObjectUtils.isEmpty(reportAwaits)) {
                break;
            }
            //将Report数据放入到本地队列
            reportAwaits.forEach(reportAwait -> {
                Report report = new Report();
                CopyUtil.REPORTAWAIT_REPORT_COPIER.copy(reportAwait, report, null);
                QueueUtil.notifyReport(report);
            });
        }
    }
    /**
     * 拉取InputWait数据分拣
     */
    @Scheduled(fixedDelay = 5 * 1000, initialDelay = 10 * 1000)
    public void loadInputWait() {

        //一直加载
        while (true) {
            List<Input> inputAwait = senderSmsService.getInputAwaitAndDel();
            if (ObjectUtils.isEmpty(inputAwait)) {
                break;
            }
            //将InputWait数据分拣
            inputAwait.forEach(input -> {
                QueueUtil.notifySorter(input, input.getPriority_Number());
            });
        }
    }
}
