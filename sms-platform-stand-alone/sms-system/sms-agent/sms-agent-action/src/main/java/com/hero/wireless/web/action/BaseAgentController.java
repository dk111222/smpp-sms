package com.hero.wireless.web.action;

import java.util.Enumeration;
import java.util.List;

import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.config.SystemKey;
import com.hero.wireless.web.entity.business.*;
import com.hero.wireless.web.entity.business.ext.AgentExt;
import com.hero.wireless.web.entity.business.ext.EnterpriseExt;
import com.hero.wireless.web.entity.business.ext.ExportFileExt;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseAgentController extends BasePaginationController {
    public final static String SHOW_PHONE_NO_LIMIT = "001000";
    private int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        this.setPageIndex(pageNum);
    }

    public Agent getLoginAgent() {
        return (Agent) (request.getSession().getAttribute(SystemKey.AGENT_INFO.toString()));
    }
    public static AgentExt currentAgent() {
        return (AgentExt) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getSession().getAttribute(SystemKey.AGENT_INFO.toString());
    }

    public String getLoginAgentName() {
        return this.getLoginAgent().getName();
    }

    public int getAgentId() {
        return this.getLoginAgent().getId();
    }

    public String getAgentNo() {
        return this.getLoginAgent().getNo();
    }

    public ExportFileExt getAgentDefaultExportFile() {
        ExportFileExt exportFile = new ExportFileExt();
        exportFile.setEnterprise_No("0");
        exportFile.setAgent_No(getLoginAgent().getNo());
        exportFile.setUser_Id(0);
        exportFile.setBlur(isBlurPhoneNo());
        return exportFile;
    }

    protected void printRequest() {
        Enumeration<String> e = request.getParameterNames();
        StringBuffer keyValue = new StringBuffer();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            keyValue.append("key:").append(key).append(",").append("value:").append(request.getParameter(key)).append(";");
        }
        SuperLogger.debug(keyValue);
    }

    protected boolean checkUserIdBelongCurrentAgent(Integer userId) {
        List<EnterpriseUser> list = DatabaseCache.getEnterpriseUserByAgentNo(getAgentNo(), null);
        if (list.size() < 0) {
            return false;
        }
        for (EnterpriseUser user : list) {
            if (user.getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    protected boolean checkEnterpriseBelongCurrentAgent(EnterpriseExt enterprise) {
        EnterpriseExt condition = new EnterpriseExt();
        condition.setAgent_No(getAgentNo());
        List<? extends Enterprise> list = DatabaseCache.getEnterpriseList(condition);
        if (list.size() < 0) {
            return false;
        }
        if (enterprise.getId() != null) {
            for (Enterprise bean : list) {
                if (enterprise.getId().equals(bean.getId())) {
                    return true;
                }
            }
        }
        if (StringUtils.isNotEmpty(enterprise.getNo())) {
            for (Enterprise bean : list) {
                if (enterprise.getNo().equals(bean.getNo())) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isBlurPhoneNo(){
        return !currentAgent().getLimits().stream().anyMatch(item-> item.getCode().equals(SHOW_PHONE_NO_LIMIT));
    }
}
