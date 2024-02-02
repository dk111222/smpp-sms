package com.hero.wireless.web.action.admin;

import com.drondea.wireless.config.Constant;
import com.drondea.wireless.util.DateTime;
import com.drondea.wireless.util.ServiceException;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.json.LayUiJsonObjectFmt;
import com.hero.wireless.json.LayUiObjectMapper;
import com.hero.wireless.json.LayuiResultUtil;
import com.hero.wireless.web.action.BaseAgentController;
import com.hero.wireless.web.action.entity.BaseParamEntity;
import com.hero.wireless.web.action.interceptor.AvoidRepeatableCommitAnnotation;
import com.hero.wireless.web.action.interceptor.OperateAnnotation;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.AgentCharge;
import com.hero.wireless.web.entity.business.AgentEnterpriseUserCharge;
import com.hero.wireless.web.entity.business.EnterpriseUser;
import com.hero.wireless.web.entity.business.ext.AgentChargeExt;
import com.hero.wireless.web.entity.business.ext.AgentEnterpriseUserChargeExt;
import com.hero.wireless.web.entity.business.ext.SmsStatisticsExt;
import com.hero.wireless.web.service.IAgentManage;
import com.hero.wireless.web.service.IChargeManage;
import com.hero.wireless.web.service.IStatisticsManage;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 财务管理
 *
 * @author gengjinbiao
 * @version 1.0.0
 * @createTime 2019年10月30日
 */
@Controller
@RequestMapping("/admin/")
public class FinanceController extends BaseAgentController {

    @Resource(name = "chargeManage")
    private IChargeManage chargeManage;
    @Resource(name = "agentManage")
    private IAgentManage agentManage;
    @Resource(name = "statisticsManage")
    private IStatisticsManage statisticsManage;

    /**
     * 充值前置
     */
    @RequestMapping("charge_preCharge")
    public String preCharge(BaseParamEntity entity) {
        if (entity.getCkIds() != null && entity.getCkIds().size() > 0) {
            if (checkUserIdBelongCurrentAgent(entity.getCkIds().get(0))) {
                EnterpriseUser enterpriseUser = DatabaseCache.getEnterpriseUserById(entity.getCkIds().get(0));
                request.setAttribute("enterpriseUser", enterpriseUser);
            }
        }
        return "/charge/charge";
    }

    /**
     * 充值
     */
    @RequestMapping("charge_charge")
    @ResponseBody
    @OperateAnnotation(moduleName = "代理商充值管理", option = "充值")
    @AvoidRepeatableCommitAnnotation(systemModuleName = AGENT_PLATFORM + "charge_charge")
    public LayUiJsonObjectFmt charge(AgentEnterpriseUserCharge charge) throws IOException {
        try {
            if (!checkUserIdBelongCurrentAgent(charge.getEnterprise_User_Id())) {
                return LayuiResultUtil.fail("查询错误");
            }
            chargeManage.agentChargeUser(getLoginAgent(), charge);
        } catch (ServiceException e) {
            return LayuiResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return LayuiResultUtil.error(e);
        }
        return LayuiResultUtil.success("操作成功");
    }

    /**
     * 用户充值记录
     */
    @RequestMapping("charge_agentEnterpriseUserChargeList")
    @ResponseBody
    public String agentEnterpriseUserChargeList(AgentEnterpriseUserChargeExt charge) {
        charge.setAgent_No(getAgentNo());
        List<AgentEnterpriseUserCharge> list = chargeManage.queryAgentEnterpriseUserChargeList(charge);
        return new LayUiObjectMapper().asSuccessString(list, charge.getPagination().getTotalCount());
    }

    /**
     * 用户充值记录 导出
     */
    @RequestMapping("charge_exportAgentEnterpriseUserChargeList")
    @ResponseBody
    public LayUiJsonObjectFmt exportAgentEnterpriseUserChargeList(AgentEnterpriseUserChargeExt charge) {
        try {
            charge.setAgent_No(getAgentNo());
            chargeManage.exportAgentEnterpriseUserChargeList(
                    DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(),
                    charge, getAgentDefaultExportFile(), Constant.PLATFORM_FLAG_AGENT);
            return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
        } catch (ServiceException e) {
            return LayuiResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return LayuiResultUtil.error(e);
        }
    }

    /**
     * 代理商充值记录
     */
    @RequestMapping("/charge_agentChargeOrderList")
    @ResponseBody
    public String agentChargeOrderList(AgentChargeExt agentChargeExt) {
        agentChargeExt.setAgent_No(getAgentNo());
        List<AgentCharge> agentChargeList = this.agentManage.queryAgentChargeList(agentChargeExt);
        return new LayUiObjectMapper().asSuccessString(agentChargeList, agentChargeExt.getPagination().getTotalCount());
    }

    /**
     * 代理商充值记录 导出
     */
    @RequestMapping("/charge_exportAgentChargeOrderList")
    @ResponseBody
    public LayUiJsonObjectFmt exportAgentChargeOrderList(AgentChargeExt agentChargeExt) {
        try {
            agentChargeExt.setAgent_No(getAgentNo());
            chargeManage.exportAgentChargeOrderList(
                    DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(),
                    agentChargeExt, getAgentDefaultExportFile(), Constant.PLATFORM_FLAG_AGENT);
            return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
        } catch (ServiceException e) {
            return LayuiResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return LayuiResultUtil.error(e);
        }
    }


    //短信报表
    @RequestMapping("statistic_smsStatisticList")
    @ResponseBody
    public String smsStatisticDailyList(HttpServletRequest request, SmsStatisticsExt smsStatisticsExt) {
        smsStatisticsExt.setAgent_No(getAgentNo());
        List<SmsStatisticsExt> pageList = this.statisticsManage.getSmsStatisticExtListByExtPage(smsStatisticsExt);
        Map<String, Object> statisticMap = this.statisticsManage.countSmsStatisticExtListByExt(smsStatisticsExt);
        Map<String, Object> map = new HashMap<String, Object>();
        return new LayUiObjectMapper().asSuccessString(pageList, Integer.valueOf(statisticMap.get("total_Record").toString()));
    }

    /**
     * 导出短信日报表
     */
    @RequestMapping("statistic_exportSmsStatisticDailyList")
    @ResponseBody
    public LayUiJsonObjectFmt exportSmsStatisticDailyList(SmsStatisticsExt smsStatisticsExt) {
        try {
            smsStatisticsExt.setAgent_No(getAgentNo());
            statisticsManage.exportSmsStatistic(
                    DatabaseCache.getCodeBySortCodeAndCode("system_env", "export_dir").getValue(),
                    smsStatisticsExt, getAgentDefaultExportFile(), "SmsStatisticByAgent");
            return new LayUiJsonObjectFmt(LayUiObjectMapper.CODE_SUCCESS, "已提交后台导出任务!");
        } catch (ServiceException e) {
            return LayuiResultUtil.fail(e.getMessage());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return LayuiResultUtil.error(e);
        }
    }

    // 短信管理 ==》实时统计前置
    @RequestMapping("statistic_preRealTimeStatistics")
    public ModelAndView preRealTimeStatistics() {
        ModelAndView mv = new ModelAndView("/charge/sms_real_time_statistics");
        String  interval = DatabaseCache.getStringValueBySortCodeAndCode("page_configuration", "page_search_interval", "");
        if (StringUtils.isNotBlank(interval)) {
            mv.addObject("minCreateDate", DateTime.getCurentTimeBeforeMinutes(Integer.valueOf(interval)));
        }
        return mv;
    }

}