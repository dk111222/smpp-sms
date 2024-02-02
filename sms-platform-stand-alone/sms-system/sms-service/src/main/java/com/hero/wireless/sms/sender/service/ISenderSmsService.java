package com.hero.wireless.sms.sender.service;

import com.hero.wireless.web.entity.send.*;

import java.util.Date;
import java.util.List;

/**
 * 短信发送业务类
 * 
 * @author Administrator
 * 
 */
public interface ISenderSmsService {

	/**
	 * 保存通知记录
	 * 
	 * @param list
	 */
	void saveReport(List<Report> list);

	/**
	 * 根据条件设置inbox
	 * @param msgBatchNo
	 * @param inputLogDate
	 * @param inbox
	 */
	void setInboxInputInfo(String msgBatchNo, Date inputLogDate, Inbox inbox);

	/**
	 * 
	* @param list
	* @author volcano
	* @date 2019年10月20日上午12:07:44
	* @version V1.0
	 */
	void saveSubmit(List<Submit> list);

	/**
	 * 插入超时的submit对象，并记录key
	 * @param submit
	 */
	void saveSubmitExpired(Submit submit);

	/**
	 * 根据提交的key查找过期的submit对象
	 * @param key
	 * @return
	 */
	Submit getSubmitExpiredByKey(String key);

	void updateExpiredSubmit(Submit submit);
	
	/**
	 * 写入上行短信
	 * 
	 * @return
	 */
	boolean saveMO(Inbox inbox);

	/**
	 *  查询需要修改状态的id
	 * @param report
	 */
    void querySubmittedId(Report report);

	/**
	 * 从redis中获取id更新发送记录状态
	 */
	void updateSubmittedById(Long id, Date crateDate);

	/**
	 * 更新发件箱状态
	 */
	void updateInputLogByIds(Long id, Date crateDate);
	
	/**
	 * 保存没有找到submit的状态报告
	 * @param reportExtraList
	 */
	void saveReportExtraList(List<ReportExtra> reportExtraList);

	/**
	 * 保存inputLogs
	 * @param inputLogs
	 */
	void saveInputLog(List<InputLog> inputLogs);


	List<Input> getInputAwaitAndDel();
	/**
	 * 获取待提交
	 * @param id
	 * @return
	 */
	List<Submit> getSubmitAwaitAndDel(Long id);

	/**
	 * 获取待推送的回执并删除
	 * @param userId
	 * @param id
	 * @param syncDel
	 * @return
	 */
	List<Report> getReportNotifyAwaitAndDel(Integer userId, Long id, boolean syncDel);

	/**
	 * 保存待处理report
	 * @param list
	 */
	void saveReportNotifyAwait(List<ReportNotifyAwait> list);

	/**
	 * 保存待处理的回执
	 * @param reportAwait
	 */
	void saveReportAwait(ReportAwait reportAwait);

	/**
	 * 获取待推送的回执并删除
	 * @param id
	 * @return
	 */
	List<ReportAwait> getReportAwaitAndDel(Long id);
}
