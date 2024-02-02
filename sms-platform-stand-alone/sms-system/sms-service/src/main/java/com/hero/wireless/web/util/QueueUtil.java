package com.hero.wireless.web.util;

import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.DataStatus;
import com.hero.wireless.enums.Priority;
import com.hero.wireless.enums.ProtocolType;
import com.hero.wireless.queue.FileQueue;
import com.hero.wireless.sms.sender.service.ISenderSmsService;
import com.hero.wireless.sort.ISortSMSService;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.dao.send.IInputAwaitDAO;
import com.hero.wireless.web.dao.send.ISubmitAwaitDAO;
import com.hero.wireless.web.entity.business.Alarm;
import com.hero.wireless.web.entity.business.EnterpriseUser;
import com.hero.wireless.web.entity.send.*;
import com.hero.wireless.web.service.IReportNotifyService;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * MQUtil
 *
 * @author Lenovo
 */
@Service
public class QueueUtil {

    private static QueueUtil queueUtil;
    public static String PLATFORM;

    @Resource(name = "defaultSenderServiceImpl")
    private ISenderSmsService senderSmsService;
    @Resource(name = "reportNotifyService")
    private IReportNotifyService reportNotifyService;
    @Resource(name = "sortSmsService")
    protected ISortSMSService sortSmsService;
//    @Resource(name = "submitExtDAO")
//    private ISubmitExtDAO submitExtDAO;
    @Resource(name = "ISubmitAwaitDAO")
    protected ISubmitAwaitDAO<SubmitAwait> submitAwaitDAO;
    @Resource(name = "IInputAwaitDAO")
    protected IInputAwaitDAO<InputAwait> inputAwaitDAO;
//    @Resource(name = "IReportNotifyAwaitDAO")
//    protected IReportNotifyAwaitDAO<ReportNotifyAwait> reportNotifyAwaitDAO;

    @PostConstruct
    public void init() {
        queueUtil = this;
    }


    private static final ConcurrentHashMap<String, Queue<Submit>> SUBMIT_QUEUE_MAP = new ConcurrentHashMap<>();

    private static final Queue<InputLog> INSERT_INPUT_LOG_QUEUE = new ConcurrentLinkedQueue<>();

    private static final Queue<Submit> INSERT_SUBMIT_QUEUE = new ConcurrentLinkedQueue<>();

    private static final Queue<Report> INSERT_REPORT_QUEUE = new ConcurrentLinkedQueue<>();

    private static final Queue<ReportNotify> INSERT_REPORT_NOTIFY_QUEUE = new ConcurrentLinkedQueue<>();

    private static final Queue<SubmitAwait> INSERT_SUBMIT_AWAIT_QUEUE = new ConcurrentLinkedQueue<>();

    private static final Queue<InputAwait> INSERT_INPUT_AWAIT_QUEUE = new ConcurrentLinkedQueue<>();
    /**
     * 下游提交的未提交到上游的缓存在队列中
     */
    public static Map<String, FileQueue<Submit>> SUBMIT_QUEUE = new HashMap<>();
    /**
     * 上游提的回执未推送到下游的缓存在队列中
     */
    public static Map<String, FileQueue<Report>> REPORT_QUEUE = new HashMap<>();
    /**
     * 上游的上行未推送到下游的存在队列中
     */
    public static Map<String, FileQueue<Inbox>> INBOX_QUEUE = new HashMap<>();

    /**
     * 将消息暂存在队列
     * @param priority
     * @param submit
     */
    public static void sendSubmit2Queue(Priority priority, Submit submit) {
        FileQueue<Submit> submitFileQueue = regSubmitQueue(submit.getChannel_No(), priority);
        if (submitFileQueue != null) {
            submitFileQueue.put(submit);
        }
    }

    public static FileQueue<Submit> regSubmitQueue(String channelNo, Priority priority) {
        String channelMQKey = channelNo + "_" + priority.value();

        FileQueue<Submit> submitFileQueue = SUBMIT_QUEUE.get(channelMQKey);
        if (submitFileQueue == null) {
            // 创建通道消息消费监听
            synchronized (channelMQKey.intern()) {
                if (!SUBMIT_QUEUE.containsKey(channelMQKey)) {
                    submitFileQueue = startFileQueue(channelMQKey, Submit.class);
                    SUBMIT_QUEUE.put(channelMQKey, submitFileQueue);
                }
                SuperLogger.debug("创建监听:" + channelMQKey);
            }
        }
        return SUBMIT_QUEUE.get(channelMQKey);
    }

    public static FileQueue<Submit> getSubmitQueue(String channelNo, Priority priority) {
        String channelMQKey = channelNo + "_" + priority.value();
        return SUBMIT_QUEUE.get(channelMQKey);
    }

    public static FileQueue<Report> regReportQueue(ProtocolType protocolType, Integer userId) {
        String channelMQKey = "report_" + protocolType.toString() + "_" + userId;
        FileQueue<Report> fileQueue = REPORT_QUEUE.get(channelMQKey);
        if (fileQueue == null) {
            // 创建通道消息消费监听
            synchronized (channelMQKey.intern()) {
                if (!REPORT_QUEUE.containsKey(channelMQKey)) {
                    fileQueue = startFileQueue(channelMQKey, Report.class);
                    REPORT_QUEUE.put(channelMQKey, fileQueue);
                }
                SuperLogger.debug("创建监听:" + channelMQKey);
            }
        }
        return REPORT_QUEUE.get(channelMQKey);
    }

    public static FileQueue<Report> getReportQueue(ProtocolType protocolType, Integer userId) {
        String channelMQKey = "report_" + protocolType.toString() + "_" + userId;
        return REPORT_QUEUE.get(channelMQKey);
    }

    public static <T> FileQueue<T>  startFileQueue(String topic, Class<T> eClass) {
        try {
            FileQueue<T> fileQueue = FileQueue.ordinary(eClass, topic);
            return fileQueue;
        } catch (Exception exception) {
            SuperLogger.error("创建队列错误：", exception);
        }
        return null;
    }

    public static FileQueue<Inbox> regInboxQueue(ProtocolType protocolType, Integer userId) {
        String channelMQKey = "inbox_" + protocolType.toString() + "_" + userId;
        FileQueue<Inbox> fileQueue = INBOX_QUEUE.get(channelMQKey);
        if (fileQueue == null) {
            // 创建通道消息消费监听
            synchronized (channelMQKey.intern()) {
                if (!INBOX_QUEUE.containsKey(channelMQKey)) {
                    fileQueue = startFileQueue(channelMQKey, Inbox.class);
                    INBOX_QUEUE.put(channelMQKey, fileQueue);
                }
                SuperLogger.debug("创建监听:" + channelMQKey);
            }
        }
        return INBOX_QUEUE.get(channelMQKey);
    }

    /**
     * 通知客户上行
     *
     * @param inbox
     */
    public static void notifyMo(Inbox inbox) {
        if (inbox == null) {
            SuperLogger.warn("inbox is null");
            return;
        }
        if (StringUtils.isBlank(inbox.getEnterprise_No())) {
            SuperLogger.warn("inbox.getEnterprise_No() is null");
            return;
        }
        EnterpriseUser user = DatabaseCache.getEnterpriseUserCachedById(inbox.getEnterprise_User_Id());
        if (!BooleanUtils.toBooleanDefaultIfNull(user.getIs_Notify_Report(), false)) {
            return;
        }
        // http
        ProtocolType protocolType = getProtocol(inbox.getProtocol_Type_Code());
        if (protocolType.equals(ProtocolType.HTTP_XML) || protocolType.equals(ProtocolType.HTTP_JSON)) {
            return;
        }
        //cmpp协议需要转换下
        if (protocolType.equals(ProtocolType.CMPP2) || protocolType.equals(ProtocolType.CMPP3)) {
            protocolType = ProtocolType.CMPP;
        }
        FileQueue<Inbox> inboxFileQueue = QueueUtil.regInboxQueue(protocolType, inbox.getEnterprise_User_Id());
        inboxFileQueue.put(inbox);
    }


    @Scheduled(fixedDelay = 1000)
    public void insertInputLog() {
        List<InputLog> insertInputLogList = new ArrayList<InputLog>();
        while (true) {
            InputLog inputLog = INSERT_INPUT_LOG_QUEUE.poll();
            if (inputLog == null) {
                break;
            }
            insertInputLogList.add(inputLog);

            if (insertInputLogList.size() > 10000) {
                SuperLogger.warn("每秒处理速度超过10000，臣妾处理不了==================insertInputLog");
                break;
            }
        }
        if (!insertInputLogList.isEmpty()) {
            ListUtils.partition(insertInputLogList, 200).forEach(item -> {
                senderSmsService.saveInputLog(item);
            });
            SuperLogger.trace("insertInputLog=======>" + insertInputLogList.size());
        }
    }

    @Scheduled(fixedDelay = 2000)
    public void insertSubmit() {
        List<Submit> insertSubmitList = new ArrayList<>();
        while (true) {
            Submit submit = INSERT_SUBMIT_QUEUE.poll();
            if (submit == null) {
                break;
            }
            insertSubmitList.add(submit);
            if (insertSubmitList.size() > 10000) {
                SuperLogger.trace("每秒处理速度超过10000，臣妾处理不了==================notifyInsertSubmit");
                break;
            }
        }

        if (!insertSubmitList.isEmpty()) {
            ListUtils.partition(insertSubmitList, 200).forEach(item -> {
                senderSmsService.saveSubmit(item);
            });
            SuperLogger.trace("notifyInsertSubmit保存条数=======>" + insertSubmitList.size());
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void notifyInsertReport() {
        List<Report> insertReportList = new ArrayList<>();
        while (true) {
            Report report = INSERT_REPORT_QUEUE.poll();
            if (report == null) {
                break;
            }
            insertReportList.add(report);
            if (insertReportList.size() > 50000) {
                break;
            }
        }

        if (!insertReportList.isEmpty()) {
            ListUtils.partition(insertReportList, 200).forEach(item -> {
                senderSmsService.saveReport(item);
            });
            SuperLogger.debug("notifyInsertReport保存条数=======>" + insertReportList.size());
        }

    }

    @Scheduled(fixedDelay = 1000)
    public void notifyInsertReportNotify() {
        List<ReportNotify> insertReportNotifyList = new ArrayList<ReportNotify>();
        while (true) {
            ReportNotify reportNotify = INSERT_REPORT_NOTIFY_QUEUE.poll();
            if (reportNotify == null) {
                break;
            }
            insertReportNotifyList.add(reportNotify);
            if (insertReportNotifyList.size() > 50000) {
                SuperLogger.warn("每秒处理速度超过50000，程序受不了==================notifyInsertReportNotify");
                break;
            }
        }
        if (!insertReportNotifyList.isEmpty()) {
            ListUtils.partition(insertReportNotifyList, 200).forEach(item -> {
                reportNotifyService.batchInsertReportNotify(item);
            });
            SuperLogger.debug("notifyInsertReportNotify保存条数=======>" + insertReportNotifyList.size());
        }
    }

    /**
     * 拉取的数据放到缓存
     * @param channelNo
     * @param submitList
     */
    public static void notifySubmitAwait(String channelNo, List<Submit> submitList) {
        Queue<Submit> queue = CacheUtil.getMapCachedQueue(getSubmitCachedKey(channelNo), SUBMIT_QUEUE_MAP);
        queue.addAll(submitList);
    }

    public static String getSubmitCachedKey(String channelNo) {
        return channelNo;
    }

    public static Submit getSubmit(String channelNo) {
        for (Priority priority : Priority.values()) {
            FileQueue<Submit> queue = QueueUtil.getSubmitQueue(channelNo, priority);
            if (queue == null) {
                continue;
            }
            Submit submit = queue.poll();
            if (submit == null) {
                continue;
            }
            return submit;
        }
        return null;
    }

    public static int getSubmitCount(String channelNo) {
        Queue<Submit> submits = SUBMIT_QUEUE_MAP.get(getSubmitCachedKey(channelNo));
        if (submits == null) {
            return 0;
        }
        return submits.size();
    }

    /**
     * 保存mo
     *
     * @param inbox
     * @return
     */
    public static void saveMo(Inbox inbox) {
        queueUtil.senderSmsService.saveMO(inbox);
    }

    /**
     * 保存状态报告
     *
     * @param entity
     * @return
     */
    public static void saveReport(Report entity) {
        saveReport(Arrays.asList(entity));
    }

    /**
     * @param submits
     * @return
     * @author volcano
     * @date 2019年10月19日下午11:51:29
     * @version V1.0
     */
    public static void saveSubmit(List<Submit> submits) {
        submits.forEach(item -> {
            INSERT_SUBMIT_QUEUE.offer(item);
        });
    }

    /**
     * @param submit
     * @return
     * @author volcano
     * @date 2020年2月26日12:09:46
     * @version V1.0
     */
    public static void saveSubmit(Submit submit) {
        INSERT_SUBMIT_QUEUE.offer(submit);
    }



    /**
     * @param inputLogs
     * @return
     * @author volcano
     * @date 2019年10月24日下午6:56:01
     * @version V1.0
     */
    public static void saveInputLogs(List<InputLog> inputLogs) {
        inputLogs.forEach(item -> {
            INSERT_INPUT_LOG_QUEUE.offer(item);
        });
    }

    /**
     * 单条
     *
     * @param inputLog
     */
    public static void notifySaveInputLog(InputLog inputLog) {
        INSERT_INPUT_LOG_QUEUE.offer(inputLog);
    }

    public static void saveReport(List<Report> reports) {
        reports.forEach(item -> {
            notifyReport(item);
            INSERT_REPORT_QUEUE.offer(item);
        });
    }

    /**
     * 通知保存通知客户记录
     *
     * @param reportNotify
     */
    public static void notifySaveReportNotify(ReportNotify reportNotify) {
        INSERT_REPORT_NOTIFY_QUEUE.offer(reportNotify);
    }

    public static void notifySorter(Input input, Integer priorityLevel) {
        input.setData_Status_Code(DataStatus.NORMAL.toString());
        try {
            queueUtil.sortSmsService.sort(input);
        } catch (Exception e) {
            SuperLogger.error("分拣错误");
            e.printStackTrace();
        }
    }

    /**
     * 通知预警
     *
     * @return
     * @author YJB
     */
    public static void notifyAlarm(Alarm alarm, Integer priorityLevel) {
        //todo 预警
    }

    public static void notifyReport(Report reportEntity) {
        if (reportEntity == null) {
            SuperLogger.warn("Report is null");
            return;
        }
        if (StringUtils.isBlank(reportEntity.getEnterprise_No())) {
            SuperLogger.warn("report.getEnterprise_No() is null");
            return;
        }
        EnterpriseUser user = DatabaseCache.getEnterpriseUserCachedById(reportEntity.getEnterprise_User_Id());
        if (user == null) {
            return;
        }
        if (!BooleanUtils.toBooleanDefaultIfNull(user.getIs_Notify_Report(), false)) {
            return;
        }

        //运营平台推送，放入await表
        if ("ADMIN".equals(PLATFORM)) {
            ReportAwait reportAwait = new ReportAwait();
            CopyUtil.REPORT_REPORTAWAIT_COPIER.copy(reportEntity, reportAwait, null);
            reportAwait.setId(null);
            queueUtil.senderSmsService.saveReportAwait(reportAwait);
            return;
        }

        // cmpp网关
        ProtocolType protocolType = getProtocol(reportEntity.getProtocol_Type_Code());
        if (protocolType.equals(ProtocolType.HTTP_XML) || protocolType.equals(ProtocolType.HTTP_JSON)) {
            ReportNotifyAwait reportNotifyAwait = new ReportNotifyAwait();
            CopyUtil.REPORT_REPORTNOTIFYAWAIT_COPIER.copy(reportEntity, reportNotifyAwait, null);
            queueUtil.senderSmsService.saveReportNotifyAwait(Arrays.asList(reportNotifyAwait));
            return;
        }

        //cmpp协议需要转换下
        if (protocolType.equals(ProtocolType.CMPP2) || protocolType.equals(ProtocolType.CMPP3)) {
            protocolType = ProtocolType.CMPP;
        }

        if (protocolType.equals(ProtocolType.SYSTEM)) {
            return;
        }

        //向队列中放回执数据
        FileQueue<Report> reportFileQueue = regReportQueue(protocolType, user.getId());
        reportFileQueue.put(reportEntity);
    }

    /**
     * 通知提交
     *
     * @param submit
     * @return
     */
    public static void notifySubmit(Submit submit, String level) {
        SubmitAwait submitAwait = new SubmitAwait();
        CopyUtil.SUBMIT_SUBMITAWAIT_COPIER.copy(submit, submitAwait, null);
        INSERT_SUBMIT_AWAIT_QUEUE.offer(submitAwait);
    }

    @Scheduled(fixedDelay = 1000)
    public void insertSubmitAwait() {
        List<SubmitAwait> insertSubmitAwaitList = new ArrayList<>();
        while (true) {
            SubmitAwait submitAwait = INSERT_SUBMIT_AWAIT_QUEUE.poll();
            if (submitAwait == null) {
                break;
            }
            insertSubmitAwaitList.add(submitAwait);
            if (insertSubmitAwaitList.size() > 50000) {
                SuperLogger.warn("每秒处理速度超过50000，臣妾处理不了==================insertSubmitLog");
                break;
            }
        }
        if (!insertSubmitAwaitList.isEmpty()) {
            ListUtils.partition(insertSubmitAwaitList, 200).forEach(item -> {
                queueUtil.submitAwaitDAO.insertList(item);
            });
            SuperLogger.trace("insertSubmitAwait=======>" + insertSubmitAwaitList.size());
        }
    }

    /**
     * input通知提交
     *
     * @param input
     * @param level
     * @return
     */
    public static void notifyInput(Input input, String level) {
        InputAwait inputAwait = new InputAwait();
        CopyUtil.INPUT_INPUTAWAIT_COPIER.copy(input, inputAwait, null);
        INSERT_INPUT_AWAIT_QUEUE.offer(inputAwait);
    }

    @Scheduled(fixedDelay = 1000)
    public void insertInputAwait() {
        List<InputAwait> insertInputAwaitList = new ArrayList<>();
        while (true) {
            InputAwait inputAwait = INSERT_INPUT_AWAIT_QUEUE.poll();
            if (inputAwait == null) {
                break;
            }
            insertInputAwaitList.add(inputAwait);
            if (insertInputAwaitList.size() > 50000) {
                SuperLogger.warn("每秒处理速度超过50000，臣妾处理不了==================insertInputLog");
                break;
            }
        }
        if (!insertInputAwaitList.isEmpty()) {
            ListUtils.partition(insertInputAwaitList, 200).forEach(item -> {
                queueUtil.inputAwaitDAO.insertList(item);
            });
            SuperLogger.trace("insertInputAwait=======>" + insertInputAwaitList.size());
        }
    }


    /**
     * @param protocol
     * @return
     * @author volcano
     * @date 2019年9月21日上午5:23:30
     * @version V1.0
     */
    private static ProtocolType getProtocol(String protocol) {
        if (StringUtils.isEmpty(protocol)) {
            return ProtocolType.WEB;
        }
        return ProtocolType.valueOf(protocol.toUpperCase());
    }

    public static void setPlatform(String platform) {
        PLATFORM = platform;
    }
}
