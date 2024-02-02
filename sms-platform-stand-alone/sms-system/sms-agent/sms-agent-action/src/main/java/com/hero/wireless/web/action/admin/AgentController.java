package com.hero.wireless.web.action.admin;

import javax.annotation.Resource;

import com.drondea.wireless.config.ResultStatus;
import com.drondea.wireless.util.CertificateUtil;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.json.LayUiObjectMapper;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.base.Pagination;
import com.hero.wireless.web.entity.business.AgentFee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drondea.wireless.util.ServiceException;
import com.hero.wireless.json.LayUiJsonObjectFmt;
import com.hero.wireless.json.LayuiResultUtil;
import com.hero.wireless.web.action.BaseAgentController;
import com.hero.wireless.web.entity.business.Agent;
import com.hero.wireless.web.service.IAgentManage;

import java.util.List;


/**
 *
 * @author gengjinbiao
 * @version 1.0.0
 * @createTime 2019年10月24日
 */
@Controller
@RequestMapping("/admin/")
public class AgentController extends BaseAgentController {

    @Resource(name = "agentManage")
    private IAgentManage agentManage;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setAutoGrowNestedPaths(true);
        binder.setAutoGrowCollectionLimit(1024);
    }


    @RequestMapping("agent_editPassword")
    @ResponseBody
    public LayUiJsonObjectFmt editPassword(String cp_newPassword, String oldPassword) {
        try {
            // rsa解密
            cp_newPassword = getPlainPassword(cp_newPassword);
            oldPassword = getPlainPassword(oldPassword);

            Agent agent = new Agent();
            agent.setId(getAgentId());
            agent.setUser_Name(getLoginAgent().getUser_Name());
            agent.setPassword(cp_newPassword);
            agentManage.editPassword(agent, oldPassword);
        } catch (ServiceException be) {
            return LayuiResultUtil.fail(be.getMessage());
        } catch (Exception be) {
            return LayuiResultUtil.fail(be.getMessage());
        }
        return LayuiResultUtil.success();
    }

    /**
     * 资费列表
     *
     */
    @RequestMapping("agent_agentFeeList")
    @ResponseBody
    public String agentFeeList(AgentFee agentFee) {
        agentFee.setAgent_No(getAgentNo());
        List<AgentFee> agentFeeList = agentManage.queryAgentFeeList(agentFee);
        if(agentFee.getPagination()==null){
            agentFee.setPagination(new Pagination(5));
        }
        return new LayUiObjectMapper().asSuccessString(agentFeeList, agentFee.getPagination().getTotalCount());
    }

    private String getPlainPassword(String encryptPassword) {
        //rsa解密
        String passwordPrivateKey = DatabaseCache.getStringValueBySystemEnvAndCode("password_private_key", "");
        String plainText = null;
        if (StringUtils.isNotBlank(passwordPrivateKey)) {
            plainText = CertificateUtil.decryptByPrivateKey(encryptPassword, passwordPrivateKey);
            if (StringUtils.isEmpty(plainText)) {
                SuperLogger.error("密码解密错误，请检查私钥");
                throw new ServiceException(ResultStatus.ERROR);
            }
        }
        return plainText;
    }

}

