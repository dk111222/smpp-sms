package com.hero.wireless.web.action.admin;

import com.drondea.wireless.config.Constant;
import com.drondea.wireless.util.DateTime;
import com.drondea.wireless.util.ServiceException;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.json.LayUiJsonObjectFmt;
import com.hero.wireless.json.LayUiObjectMapper;
import com.hero.wireless.json.LayuiResultUtil;
import com.hero.wireless.json.SmsUIObjectMapper;
import com.hero.wireless.web.action.BaseAgentController;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.ExportFile;
import com.hero.wireless.web.entity.business.ext.ExportFileExt;
import com.hero.wireless.web.entity.ext.SqlStatisticsEntity;
import com.hero.wireless.web.entity.send.Inbox;
import com.hero.wireless.web.entity.send.Report;
import com.hero.wireless.web.entity.send.ReportNotify;
import com.hero.wireless.web.entity.send.Submit;
import com.hero.wireless.web.entity.send.ext.InboxExt;
import com.hero.wireless.web.entity.send.ext.InputLogExt;
import com.hero.wireless.web.entity.send.ext.ReportExt;
import com.hero.wireless.web.entity.send.ext.SubmitExt;
import com.hero.wireless.web.service.IReportNotifyService;
import com.hero.wireless.web.service.ISendManage;
import com.hero.wireless.web.service.facede.SubmitDetailsFacede;
import com.hero.wireless.web.util.BeanUtil;
import com.drondea.wireless.util.BlurUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/")
public class SendedController extends BaseAgentController {

    @Resource(name = "sendManage")
    private ISendManage sendManage;
    @Resource(name = "reportNotifyService")
    private IReportNotifyService reportNotifyService;

    @InitBinder
    public void initDateFormate(WebDataBinder dataBinder) {
        dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    // 短信发件箱前置
    @RequestMapping("sended_preInputLogList")
    public ModelAndView preInputLogList() {
        ModelAndView mv = new ModelAndView("/sended/input_log_list");
        String  interval = DatabaseCache.getStringValueBySortCodeAndCode("page_configuration", "page_search_interval", "");
        if (StringUtils.isNotBlank(interval)) {
            mv.addObject("minCreateDate", DateTime.getCurentTimeBeforeMinutes(Integer.valueOf(interval)));
        }
        return mv;
    }

    // 发送记录前置
    @RequestMapping("sended_preSubmitList")
    public ModelAndView preSubmitList() {
        ModelAndView mv = new ModelAndView("/sended/submit_list");
        String  interval = DatabaseCache.getStringValueBySortCodeAndCode("page_configuration", "page_search_interval", "");
        if (StringUtils.isNotBlank(interval)) {
            mv.addObject("minCreateDate", DateTime.getCurentTimeBeforeMinutes(Integer.valueOf(interval)));
        }
        return mv;
    }

    // 短信管理 ==》发送回执前置
    @RequestMapping("sended_preReportList")
    public ModelAndView preReportList() {
        ModelAndView mv = new ModelAndView("/sended/report_list");
        String  interval = DatabaseCache.getStringValueBySortCodeAndCode("page_configuration", "page_search_interval", "");
        if (StringUtils.isNotBlank(interval)) {
            mv.addObject("minCreateDate", DateTime.getCurentTimeBeforeMinutes(Integer.valueOf(interval)));
        }
        return mv;
    }

    // 发件箱
    @RequestMapping("sended_inputLogList")
    @ResponseBody
    public String inputLogList(InputLogExt inputLogExt) {
       try {
        inputLogExt.setAgent_No(getAgentNo());
        List<InputLogExt> inputLogExtList = sendManage.queryInputLogList(inputLogExt);
        return new SmsUIObjectMapper(isBlurPhoneNo()).asSuccessString(inputLogExtList, inputLogExt.getPagination());
      } catch (Exception e) {
        String s = e.getMessage();
        String substring = s.substring(s.length()-13);
        if(("doesn't exist").equals(substring)) {
            return new SmsUIObjectMapper().asFaildString("超出可查询时间");
        }
        return new SmsUIObjectMapper().asFaildString(e.getMessage());
    }
    }

    // 发件箱数据统计
    @RequestMapping("sended_queryInputLogListTotalData")
    @ResponseBody
    public String queryInputLogListTotalData(InputLogExt inputLogExt) {
        inputLogExt.setAgent_No(getAgentNo());
        SqlStatisticsEntity entity = sendManage.queryInputLogListTotalData(inputLogExt);
        return new LayUiObjectMapper().asSuccessString(entity);
    }

    /**
     * 导出发件箱
     *
     * @return
     */
    @RequestMapping("sended_exportInputLogList")
    @ResponseBody
    public LayUiJsonObjectFmt exportInputLogList(InputLogExt inputLogExt) throws Exception {
        if (inputLogExt == null) {
            inputLogExt = new InputLogExt();
        }
        inputLogExt.setAgent_No(getAgentNo());
        BeanUtil.stringPropertyEncodeConvert(inputLogExt.getClass(), inputLogExt, "iso8859-1", "utf-8");
        sendManage.exportInputLog(DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(),
                inputLogExt, getAgentDefaultExportFile());
        return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
    }

    /**
     * 收件箱
     *
     * @return
     */
    @RequestMapping("sended_inboxList")
    @ResponseBody
    public String inboxList(InboxExt inbox) {
        inbox.setAgent_No(getAgentNo());
        List<Inbox> inboxList = sendManage.queryInboxList(inbox);
        return new SmsUIObjectMapper(isBlurPhoneNo()).asSuccessString(inboxList, inbox.getPagination().getTotalCount());
    }

    // 发送记录
    @RequestMapping("sended_submitList")
    @ResponseBody
    public String submitList(SubmitExt submitExt) {
        try {
        submitExt.setAgent_No(getAgentNo());
        submitExt.setMaxSubmitDate(DateTime.getTimeForMaxMillisecond(submitExt.getMaxSubmitDate()));
        List<SubmitExt> submitExtList = sendManage.querySubmitListSharding(submitExt);
        return new SmsUIObjectMapper(isBlurPhoneNo()).asSuccessString(submitExtList, submitExt.getPagination().getTotalCount(),
                submitExt.getStatisticsEntity());
    } catch (Exception e) {
        String s = e.getMessage();
        String substring = s.substring(s.length()-13);
        if(("doesn't exist").equals(substring)) {
            return new SmsUIObjectMapper().asFaildString("超出可查询时间");
        }
        return new SmsUIObjectMapper().asFaildString(e.getMessage());
    }
    }

    // 发送记录列表统计
    @RequestMapping("sended_querySubmitListTotalData")
    @ResponseBody
    public String querySubmitListTotalData(SubmitExt submitExt) {
        submitExt.setAgent_No(getAgentNo());
        SqlStatisticsEntity sqlStatisticsEntity = sendManage.querySubmitListTotalData(submitExt);
        return new LayUiObjectMapper().asSuccessString(sqlStatisticsEntity);
    }

    /**
     * 短信管理 ==》发送记录==》点击详情
     *
     * @return
     */
    @RequestMapping("sended_reportIndex")
    public String reportIndex(String channel_Master_Msg_No, String limitCode, String flag, String minCreateDate,
                              String msg_Batch_No) {
        request.setAttribute("channel_Master_Msg_No", "undefined".equals(channel_Master_Msg_No) ? "" : channel_Master_Msg_No);
        request.setAttribute("msg_Batch_No", "undefined".equals(msg_Batch_No) ? "" : msg_Batch_No);
        request.setAttribute("limitCode", limitCode);
        request.setAttribute("flag", flag);
        if (!StringUtils.isEmpty(minCreateDate) && minCreateDate.length() > 10) {
            minCreateDate = minCreateDate.substring(0, 10) + " 00:00:00";
            String maxCreateDate = minCreateDate.substring(0, 10) + " 23:59:59";
            request.setAttribute("minCreateDate", minCreateDate);
            request.setAttribute("maxCreateDate", maxCreateDate);
        }
        return "/sended/report_list";
    }

    /**
     * 导出发送记录
     *
     * @param submitExt
     * @return
     */
    @RequestMapping("sended_exportSubmitList")
    @ResponseBody
    public LayUiJsonObjectFmt exportSubmitList(SubmitExt submitExt) {
        if (submitExt == null) {
            submitExt = new SubmitExt();
        }
        submitExt.setAgent_No(getAgentNo());
        BeanUtil.stringPropertyEncodeConvert(submitExt.getClass(), submitExt, "iso8859-1", "utf-8");
        sendManage.exportSubmit(DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(),
                submitExt, getAgentDefaultExportFile(), Constant.PLATFORM_FLAG_AGENT);
        return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
    }

    /**
     * 短信管理 ==》发送明细
     *
     * @param reportExt flag: Submit_Flag 从发送记录点击详情 Input_Log_Flag 从发件箱点击详情 null
     *                  直接点击发送明细菜单
     * @return
     */
    @RequestMapping("sended_reportList")
    @ResponseBody
    public String reportList(ReportExt reportExt, String flag) {
        try {
        reportExt.setAgent_No(getAgentNo());
        reportExt.setMaxSubmitDate(DateTime.getTimeForMaxMillisecond(reportExt.getMaxSubmitDate()));
        //发送记录详情
        if ("Submit_Flag".equals(flag) && StringUtils.isEmpty(reportExt.getChannel_Msg_Id())) {
            return new LayUiObjectMapper().asSuccessString(null, 0);
        }
        //提交记录详情
        if ("Input_Log_Flag".equals(flag) && StringUtils.isEmpty(reportExt.getMsg_Batch_No())) {
            return new LayUiObjectMapper().asSuccessString(null, 0);
        }
        List<ReportExt> reportExtList = sendManage.queryReportListSharding(reportExt);
        return new SmsUIObjectMapper(isBlurPhoneNo()).asSuccessString(reportExtList, reportExt.getPagination().getTotalCount());
    } catch (Exception e) {
        String s = e.getMessage();
        String substring = s.substring(s.length()-13);
        if(("doesn't exist").equals(substring)) {
            return new SmsUIObjectMapper().asFaildString("超出可查询时间");
        }
        return new SmsUIObjectMapper().asFaildString(e.getMessage());
    }
    }

    //发送明细数据统计
    @RequestMapping("sended_queryReportListTotalData")
    @ResponseBody
    public String queryReportListTotalData(ReportExt reportExt) {
        reportExt.setAgent_No(getAgentNo());
        SqlStatisticsEntity sqlStatisticsEntity = sendManage.queryReportListTotalData(reportExt);
        return new LayUiObjectMapper().asSuccessString(sqlStatisticsEntity);
    }

    /**
     * 导出发送回执
     *
     * @param reportExt
     * @return
     */
    @RequestMapping("sended_exportReportList")
    @ResponseBody
    public LayUiJsonObjectFmt exportReportList(ReportExt reportExt) {
        reportExt.setAgent_No(getAgentNo());
        if ("null".equals(reportExt.getChannel_Msg_Id()) || "".equals(reportExt.getChannel_Msg_Id())) {
            reportExt.setChannel_Msg_Id(null);
        }
        BeanUtil.stringPropertyEncodeConvert(reportExt.getClass(), reportExt, "iso8859-1", "utf-8");
        sendManage.exportReport(DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(),
                reportExt, getAgentDefaultExportFile(), Constant.PLATFORM_FLAG_AGENT);
        return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
    }

    //导出下载列表
    @RequestMapping("sended_exportFileList")
    @ResponseBody
    public String exportFileList(ExportFileExt exportFile) {
        exportFile.setAgent_No(getAgentNo());
        exportFile.setEnterprise_No("0");
        exportFile.setUser_Id(0);
        List<ExportFile> exportFileList = sendManage.queryExportFile(exportFile);
        return new LayUiObjectMapper().asSuccessString(exportFileList, exportFile.getPagination().getTotalCount());
    }

    /**
     * 文件下载
     **/
    @RequestMapping("sended_downloadFile")
    @ResponseBody
    public void downloadFile(HttpServletResponse response, Integer id) {
        try {
            ExportFile exportFile = getAgentDefaultExportFile();
            exportFile.setId(id);
            sendManage.downloadFile(response, exportFile);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 短信管理 ==》未知记录列表
     */
    @RequestMapping("sended_agentReportUnknownList")
    @ResponseBody
    public String reportUnknownList(SubmitExt submitExt) {
        try {
            submitExt.setAgent_No(getAgentNo());
            List<SubmitExt> submitExtList = sendManage.querySubmitUnknownList(submitExt);
            return new SmsUIObjectMapper(isBlurPhoneNo()).asSuccessString(submitExtList, submitExt.getPagination());
        } catch (ServiceException se) {
            return new SmsUIObjectMapper().asFaildString(se.getMessage());
        } catch (Exception e) {
            String s = e.getMessage();
            String substring = s.substring(s.length()-13);
            if(("doesn't exist").equals(substring)|(" table config").equals(substring)) {
                return new SmsUIObjectMapper().asFaildString("超出可查询时间");
            }
            return new SmsUIObjectMapper().asFaildString("查询异常");
        }
    }

    /**
     * 短信管理 ==》未知记录导出
     */
    @RequestMapping("sended_agentExportSubmitReportUnknownList")
    @ResponseBody
    public LayUiJsonObjectFmt agentexportSubmitReportUnknownList(SubmitExt submitExt) {
        submitExt.setAgent_No(getAgentNo());
        try {
            sendManage.exportSubmitReportUnknownList(
                    DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue()
                    , submitExt, getAgentDefaultExportFile(), Constant.PLATFORM_FLAG_AGENT);
            return LayuiResultUtil.success("已提交后台导出任务!");
        } catch (ServiceException e) {
            return LayuiResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return LayuiResultUtil.error(e);
        }

    }

    /**
     * 发送记录 》》》查看详情
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("sended_submitDetails")
    public ModelAndView submitDetails(SubmitExt submitExt) throws Exception {
        ModelAndView mv = new ModelAndView("/sended/submit_details");
        submitExt.setAgent_No(getAgentNo());
        Date date = DateTime.getDate(submitExt.getSubmit_Date_Str());
        submitExt.setMinSubmitDate(DateTime.getStartOfDay(date));
        submitExt.setMaxSubmitDate(DateTime.getEndOfDay(date));
        List<Submit> submitList = this.sendManage.querySubmitByPrimaryKey(submitExt);
        if (submitList.size() > 0) {
            Submit submit = submitList.get(0);
            submitExt.setPhone_No(submit.getPhone_No());
            submitExt.setChannel_Msg_Id(submit.getChannel_Msg_Id());
            submitExt.setMsg_Batch_No(submit.getMsg_Batch_No());
            List<Report> reportList = this.sendManage.queryReportListBySubmit(submitExt);
            List<ReportNotify> reportNotifyList = reportNotifyService.queryReportNotifyListBySubmit(submitExt);
            if(!isBlurPhoneNo()){
                submit.setPhone_No(BlurUtil.bathPhoneNoBlur(submit.getPhone_No()));
            }
            SubmitDetailsFacede submitDetailsFacede = new SubmitDetailsFacede(submit, reportList, reportNotifyList);
            mv.addObject("details", submitDetailsFacede);
        }
        return mv;
    }

}
