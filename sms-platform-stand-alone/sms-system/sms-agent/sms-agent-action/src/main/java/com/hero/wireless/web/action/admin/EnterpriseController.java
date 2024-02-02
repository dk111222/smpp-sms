package com.hero.wireless.web.action.admin;

import com.drondea.wireless.util.ServiceException;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.AuthCode;
import com.hero.wireless.enums.ContentAuditType;
import com.hero.wireless.enums.DataSourceType;
import com.hero.wireless.json.LayUiJsonObjectFmt;
import com.hero.wireless.json.LayUiObjectMapper;
import com.hero.wireless.json.LayuiResultUtil;
import com.hero.wireless.json.SmsUIObjectMapper;
import com.hero.wireless.web.action.BaseAgentController;
import com.hero.wireless.web.action.entity.BaseParamEntity;
import com.hero.wireless.web.action.interceptor.AvoidRepeatableCommitAnnotation;
import com.hero.wireless.web.action.interceptor.OperateAnnotation;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.config.SystemKey;
import com.hero.wireless.web.entity.business.*;
import com.hero.wireless.web.entity.business.ext.EnterpriseExt;
import com.hero.wireless.web.entity.business.ext.EnterpriseUserExt;
import com.hero.wireless.web.entity.ext.SqlStatisticsEntity;
import com.hero.wireless.web.service.IChargeManage;
import com.hero.wireless.web.service.IEnterpriseManage;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 企业Action
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin/")
public class EnterpriseController extends BaseAgentController {

    @Resource(name = "enterpriseManage")
    private IEnterpriseManage enterpriseManage;
    @Resource(name = "chargeManage")
    private IChargeManage chargeManage;

    @InitBinder
    public void initDateFormate(WebDataBinder dataBinder) {
        dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    // 企业列表
    @RequestMapping("enterprise_list")
    @ResponseBody
    public String list(EnterpriseExt enterpriseExt) {
        enterpriseExt = enterpriseExt == null ? new EnterpriseExt() : enterpriseExt;
        enterpriseExt.setAgent_No(getAgentNo());
        List<Enterprise> enterpriseList = enterpriseManage.queryEnterpriseList(enterpriseExt);
        return new SmsUIObjectMapper().asSuccessString(enterpriseList, enterpriseExt.getPagination());
    }

    // 异步数据统计
    @RequestMapping("enterprise_queryEnterpriseListTotalData")
    @ResponseBody
    public String queryEnterpriseListTotalData(EnterpriseExt enterpriseExt) {
        enterpriseExt = enterpriseExt == null ? new EnterpriseExt() : enterpriseExt;
        enterpriseExt.setAgent_No(getAgentNo());
        SqlStatisticsEntity entity = enterpriseManage.queryEnterpriseListTotalData(enterpriseExt);
        return new LayUiObjectMapper().asSuccessString(entity);
    }

    @RequestMapping("enterprise_add")
    @ResponseBody
    @OperateAnnotation(moduleName = "企业管理", option = "添加企业")
    @AvoidRepeatableCommitAnnotation(systemModuleName = AGENT_PLATFORM + "enterprise_add")
    public LayUiJsonObjectFmt add(Enterprise enterprise) {
        try {
            enterprise.setAgent_No(getAgentNo());
            enterprise.setData_Source(DataSourceType.PLATFORMADD.toString());// 企业数据来源：00：后台添加 01：自主注册
            enterprise.setAuthentication_State_Code(AuthCode.PASSED.toString());// 认证状态:00:未认证 01：认证通过 02：认证拒绝
            this.enterpriseManage.addEnterprise(enterprise);
        } catch (Exception e) {
            SuperLogger.error(e);
            return LayuiResultUtil.fail(e.getMessage());
        }
        return LayuiResultUtil.success();
    }

    /**
     * 修改企业前置
     *
     * @return
     */
    @RequestMapping("enterprise_preEdit")
    public String preEdit(BaseParamEntity entity) {
        if (entity.getCkIds() == null || entity.getCkIds().size() != 1) {
            throw new ServiceException("至少选择一条数据");
        }
        EnterpriseExt enterpriseExt = new EnterpriseExt();
        enterpriseExt.setId(entity.getCkIds().get(0));
        enterpriseExt.setAgent_No(getAgentNo());
        List<Enterprise> enterpriseList = enterpriseManage.queryEnterpriseList(enterpriseExt);
        if (!enterpriseList.isEmpty()) {
            request.setAttribute("eBean", enterpriseList.get(0));
        }
        return "/enterprise/enterprise_edit";
    }

    // 编辑企业用户前置
    @RequestMapping("enterprise_preEditUser")
    public String preEditUser(BaseParamEntity entity) {
        if (entity.getCkIds() == null || entity.getCkIds().size() != 1) {
            throw new ServiceException("至少选择一条数据");
        }
        List<String> enterpriseNoList = DatabaseCache.getEnterpriseNoByAgentNo(getLoginAgent());
        EnterpriseUserExt enterpriseUserExt = new EnterpriseUserExt();
        enterpriseUserExt.setId(entity.getCkIds().get(0));
        enterpriseUserExt.setEnterpriseNoList(enterpriseNoList);
        List<EnterpriseUser> enterpriseUserList = this.enterpriseManage.queryEnterpriseUserList(enterpriseUserExt);
        if (!enterpriseUserList.isEmpty()) {
            request.setAttribute("enterpriseUser", enterpriseUserList.get(0));
        }
        return "/enterprise/edit_user";
    }

    /**
     * 编辑保存企业用户
     *
     * @return
     */
    @RequestMapping("enterprise_editUser")
    @ResponseBody
    @OperateAnnotation(moduleName = "企业管理", option = "修改企业用户")
    public LayUiJsonObjectFmt editUser(EnterpriseUserExt enterpriseUser) {
        try {
            if (enterpriseUser.getPassword() != null
                    && !enterpriseUser.getPassword().equals(enterpriseUser.getConfirmPassword())) {
                return LayuiResultUtil.fail("两次密码输入不一致");
            }
            List<String> enterpriseNoList = DatabaseCache.getEnterpriseNoByAgentNo(getLoginAgent());
            enterpriseUser.setEnterpriseNoList(enterpriseNoList);
            this.enterpriseManage.editUser(enterpriseUser);
        } catch (Exception e) {
            return LayuiResultUtil.fail(e.getMessage());
        }
        return LayuiResultUtil.success();
    }

    /**
     * 编辑保存
     *
     * @return
     */
    @RequestMapping("enterprise_edit")
    @ResponseBody
    @OperateAnnotation(moduleName = "企业管理", option = "修改企业信息")
    public LayUiJsonObjectFmt edit(EnterpriseExt enterpriseExt) throws Exception {
        enterpriseExt.setAgent_No(getAgentNo());
        this.enterpriseManage.editEnterprise(enterpriseExt);
        return LayuiResultUtil.success();
    }

    /**
     * 增加企业用户
     *
     * @return
     */
    @RequestMapping("enterprise_addUser")
    @ResponseBody
    @OperateAnnotation(moduleName = "企业管理", option = "增加企业用户")
    @AvoidRepeatableCommitAnnotation(systemModuleName = AGENT_PLATFORM + "enterprise_addUser")
    public LayUiJsonObjectFmt addUser(EnterpriseUserExt enterpriseUser) {
        try {
            if (enterpriseUser.getPassword() == null
                    || !enterpriseUser.getPassword().equals(enterpriseUser.getConfirmPassword())) {
                return LayuiResultUtil.fail("两次密码输入不一致");
            }
            if (StringUtils.isEmpty(enterpriseUser.getEnterprise_No())) {
                return LayuiResultUtil.fail("请选择企业！");
            }
            int agent_type = DatabaseCache.getIntValueBySortCodeAndCode("agent_type", "exclusive", 1);
            Agent agent = getLoginAgent();
            if(agent.getType_Code().intValue() == agent_type){
                //独家代理设置成待审
                enterpriseUser.setAudit_Type_Code(ContentAuditType.AUDIT.toString());
            }
            this.enterpriseManage.addEnterpriseUser(enterpriseUser);
        } catch (Exception e) {
            return LayuiResultUtil.fail(e.getMessage());
        }
        return LayuiResultUtil.success();
    }

    @RequestMapping("enterprise_userIndex")
    public String userIndex(Integer ckIds, String limitCode) {
        Enterprise enterprise = enterpriseManage.queryEnterpriseById(ckIds);
        request.setAttribute("enterprise_No", enterprise == null ? "-1" : enterprise.getNo());
        request.setAttribute("limitCode", limitCode);
        setParam(request);
        return "/enterprise/user_list";
    }

    @RequestMapping("enterprise_perUserlist")
    public String perUserlist() {
        setParam(request);
        return "/enterprise/user_list";
    }

    private void setParam(HttpServletRequest request) {
        String apiDocUrl = DatabaseCache.getStringValueBySystemEnvAndCode("api_doc_url", "https://www.showdoc.com.cn/872195016401251?page_id=4702298269132771");
        Code netwayHttpIp = DatabaseCache.getCodeBySortCodeAndCode("netway_http", "netway_http_ip");
        Code netwayHttpPort = DatabaseCache.getCodeBySortCodeAndCode("netway_http", "netway_http_port");
        Code netwayCmppIp = DatabaseCache.getCodeBySortCodeAndCode("netway_cmpp", "netway_cmpp_ip");
        Code netwayCmppPort = DatabaseCache.getCodeBySortCodeAndCode("netway_cmpp", "netway_cmpp_port");
        Code netwaySmppPort = DatabaseCache.getCodeBySortCodeAndCode("netway_smpp", "netway_smpp_port");
        Code netwaySgipPort = DatabaseCache.getCodeBySortCodeAndCode("netway_sgip", "netway_sgip_port");
        Code netwaySmgpPort = DatabaseCache.getCodeBySortCodeAndCode("netway_smgp", "netway_smgp_port");
        request.setAttribute("apiDocUrl", apiDocUrl);
        request.setAttribute("netwayHttpIp", netwayHttpIp);
        request.setAttribute("netwayHttpPort", netwayHttpPort);
        request.setAttribute("netwayCmppIp", netwayCmppIp);
        request.setAttribute("netwayCmppPort", netwayCmppPort);
        request.setAttribute("netwaySmppPort", netwaySmppPort);
        request.setAttribute("netwaySgipPort", netwaySgipPort);
        request.setAttribute("netwaySmgpPort", netwaySmgpPort);
    }

    @RequestMapping("enterprise_userList")
    @ResponseBody
    public String userList(EnterpriseUserExt enterpriseUserExt) {
        List<String> enterpriseNoList = DatabaseCache.getEnterpriseNoByAgentNo(getLoginAgent());
        enterpriseUserExt.setEnterpriseNoList(enterpriseNoList);
        List<EnterpriseUser> enterpriseUserList = enterpriseManage.queryEnterpriseUserList(enterpriseUserExt);
        return new LayUiObjectMapper().asSuccessString(enterpriseUserList,
                enterpriseUserExt.getPagination().getTotalCount());
    }

    @RequestMapping("enterprise_lockEnterprise")
    @ResponseBody
    @OperateAnnotation(moduleName = "企业管理", option = "锁定企业")
    public LayUiJsonObjectFmt lockEnterprise(BaseParamEntity entity) {
        for (Integer id : entity.getCkIds()) {
            EnterpriseExt enterprise = new EnterpriseExt();
            enterprise.setId(id);
            if (!checkEnterpriseBelongCurrentAgent(enterprise)) {
                return LayuiResultUtil.fail("查询错误");
            }
        }
        this.enterpriseManage.updateEnterpriseStatus(entity.getCkIds(), "Locked");
        return LayuiResultUtil.success();
    }

    @RequestMapping("enterprise_unLockEnterprise")
    @ResponseBody
    @OperateAnnotation(moduleName = "企业管理", option = "解锁企业")
    public LayUiJsonObjectFmt unLockEnterprise(BaseParamEntity entity) {
        for (Integer id : entity.getCkIds()) {
            EnterpriseExt enterprise = new EnterpriseExt();
            enterprise.setId(id);
            if (!checkEnterpriseBelongCurrentAgent(enterprise)) {
                return LayuiResultUtil.fail("查询错误");
            }
        }
        Enterprise enterprise = new Enterprise();
        enterprise.setAgent_No(getAgentNo());
        this.enterpriseManage.updateEnterpriseStatus(entity.getCkIds(), "Normal");
        return LayuiResultUtil.success();
    }

    @RequestMapping("enterprise_lockUser")
    @ResponseBody
    @OperateAnnotation(moduleName = "用户管理", option = "锁定企业用户")
    public LayUiJsonObjectFmt lockUser(BaseParamEntity entity) {
        for (Integer id : entity.getCkIds()) {
            if (!checkUserIdBelongCurrentAgent(id)) {
                return LayuiResultUtil.fail("查询错误");
            }
        }
        this.enterpriseManage.updateEnterpriseUserStatus(entity.getCkIds(), "Locked");
        return LayuiResultUtil.success();
    }

    @RequestMapping("enterprise_unLockUser")
    @ResponseBody
    @OperateAnnotation(moduleName = "用户管理", option = "解锁企业用户")
    public LayUiJsonObjectFmt unLockUser(BaseParamEntity entity) {
        for (Integer id : entity.getCkIds()) {
            if (!checkUserIdBelongCurrentAgent(id)) {
                return LayuiResultUtil.fail("查询错误");
            }
        }
        this.enterpriseManage.updateEnterpriseUserStatus(entity.getCkIds(), "Normal");
        return LayuiResultUtil.success();
    }

    @RequestMapping("enterprise_enterpriseUserFeeIndex")
    public String enterpriseUserFeeIndex(String limitCode, String ckIds) {
        request.setAttribute("limitCode", limitCode);
        request.setAttribute("enterpriseUserId", ckIds);
        return "/enterprise/enterprise_user_fee_list";
    }

    /**
     * 资费列表
     *
     * @param
     * @return
     */
    @RequestMapping("enterprise_enterpriseUserFeeList")
    @ResponseBody
    public String enterpriseUserFeeList(EnterpriseUserFee enterpriseUserFee) {
        if (!checkUserIdBelongCurrentAgent(enterpriseUserFee.getEnterprise_User_Id())) {
            return null;
        }
        List<EnterpriseUserFee> enterpriseUserFeeList = enterpriseManage.queryEnterpriseUserFeeList(enterpriseUserFee);
        return new LayUiObjectMapper().asSuccessString(enterpriseUserFeeList, enterpriseUserFee.getPagination().getTotalCount());
    }

    /**
     * 保存资费
     */
    @RequestMapping("enterprise_addEnterpriseUserFee")
    @ResponseBody
    @OperateAnnotation(moduleName = "用户管理", option = "添加资费")
    @AvoidRepeatableCommitAnnotation(systemModuleName = AGENT_PLATFORM + "enterprise_addEnterpriseUserFee")
    public LayUiJsonObjectFmt addEnterpriseUserFee(EnterpriseUserFee enterpriseUserFee) {
        try {
            this.enterpriseManage.addEnterpriseUserFee(enterpriseUserFee);
        } catch (ServiceException se) {
            se.printStackTrace();
            return LayuiResultUtil.fail(se.getMessage());
        }
        return LayuiResultUtil.success();
    }

    /**
     * 资费修改前置
     */
    @RequestMapping("enterprise_preEditEnterpriseUserFee")
    public ModelAndView preEditEnterpriseUserFee(BaseParamEntity entity) {
        ModelAndView mv = new ModelAndView();
        EnterpriseUserFee enterpriseUserFee = new EnterpriseUserFee();
        enterpriseUserFee.setId(entity.getCkIds().get(0));
        enterpriseUserFee = this.enterpriseManage.queryEnterpriseUserFeeList(enterpriseUserFee).get(0);
        mv.setViewName("/enterprise/enterprise_user_fee_edit");
        if (checkUserIdBelongCurrentAgent(enterpriseUserFee.getEnterprise_User_Id())) {
            mv.addObject("enterpriseUserFee", enterpriseUserFee);
        }
        return mv;
    }

    /**
     * 资费修改
     */
    @RequestMapping("enterprise_editEnterpriseUserFee")
    @ResponseBody
    @OperateAnnotation(moduleName = "用户管理", option = "资费修改")
    @AvoidRepeatableCommitAnnotation(systemModuleName = AGENT_PLATFORM + "enterprise_editEnterpriseUserFee")
    public LayUiJsonObjectFmt editEnterpriseUserFee(EnterpriseUserFee enterpriseUserFee) {
        try {
            EnterpriseUser enterprise = DatabaseCache.getEnterpriseUserById(enterpriseUserFee.getEnterprise_User_Id());
            if (!checkUserIdBelongCurrentAgent(enterprise.getId())) {
                return LayuiResultUtil.fail("查询错误");
            }
            this.enterpriseManage.editEnterpriseUserFee(enterpriseUserFee);
        } catch (ServiceException se) {
            se.printStackTrace();
            return LayuiResultUtil.fail(se.getMessage());
        }
        return LayuiResultUtil.success();
    }

    /**
     * 删除资费
     */
    @RequestMapping("enterprise_delEnterpriseUserFee")
    @ResponseBody
    @OperateAnnotation(moduleName = "用户管理", option = "删除资费")
    @AvoidRepeatableCommitAnnotation(systemModuleName = AGENT_PLATFORM + "enterprise_delEnterpriseUserFee")
    public LayUiJsonObjectFmt delEnterpriseUserFee(BaseParamEntity entity) {
        if (ObjectUtils.isEmpty(entity.getCkIds())) {
            return LayuiResultUtil.success();
        }
        EnterpriseUserExt userExt = new EnterpriseUserExt();
        userExt.setEnterpriseNoList(DatabaseCache.getEnterpriseNoByAgentNo(getLoginAgent()));
        this.enterpriseManage.delEnterpriseUserFee(entity.getCkIds(), userExt);
        return LayuiResultUtil.success();
    }

}