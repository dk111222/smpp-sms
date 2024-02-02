package com.hero.wireless.web.entity.business.ext;

import com.hero.wireless.web.entity.business.AgentLimit;
import com.hero.wireless.web.entity.business.AgentRole;

public class AgentLimitExt extends AgentLimit {
	private AgentRole agentRole;

    public AgentRole getAgentRole() {
        return agentRole;
    }

    public void setAgentRole(AgentRole agentRole) {
        this.agentRole = agentRole;
    }
}
