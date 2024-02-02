package com.hero.wireless.web.entity.business.ext;

import com.hero.wireless.web.entity.business.Agent;
import com.hero.wireless.web.entity.business.AgentLimit;
import com.hero.wireless.web.entity.business.AgentRole;

import java.math.BigDecimal;
import java.util.List;

public class AgentExt extends Agent {
    private List<AgentRole> roles;
    private List<AgentLimit> limits;
    private List<AgentLimit> limitOrderIds;

    private BigDecimal min_Available_Money;//最小可用金额

    private BigDecimal max_Available_Money;//最大可用金额
    private String confirmPassword;
    private String agentChargeMultiple;//代理商预充值倍数

    public String getAgentChargeMultiple() {
        return agentChargeMultiple;
    }

    public void setAgentChargeMultiple(String agentChargeMultiple) {
        this.agentChargeMultiple = agentChargeMultiple;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public BigDecimal getMin_Available_Money() {
        return min_Available_Money;
    }

    public void setMin_Available_Money(BigDecimal min_Available_Money) {
        this.min_Available_Money = min_Available_Money;
    }

    public BigDecimal getMax_Available_Money() {
        return max_Available_Money;
    }

    public void setMax_Available_Money(BigDecimal max_Available_Money) {
        this.max_Available_Money = max_Available_Money;
    }

    public List<AgentRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AgentRole> roles) {
        this.roles = roles;
    }

    public List<AgentLimit> getLimits() {
        return limits;
    }

    public void setLimits(List<AgentLimit> limits) {
        this.limits = limits;
    }

    public List<AgentLimit> getLimitOrderIds() {
        return limitOrderIds;
    }

    public void setLimitOrderIds(List<AgentLimit> limitOrderIds) {
        this.limitOrderIds = limitOrderIds;
    }
}