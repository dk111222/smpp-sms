package com.hero.wireless.web.entity.business.ext;

import com.hero.wireless.web.entity.business.Agent;
import com.hero.wireless.web.entity.business.AgentRole;

public class AgentRoleExt extends AgentRole {
	private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
