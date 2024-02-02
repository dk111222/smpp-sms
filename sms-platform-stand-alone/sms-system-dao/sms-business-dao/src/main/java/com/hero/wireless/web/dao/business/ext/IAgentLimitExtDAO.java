package com.hero.wireless.web.dao.business.ext;

import com.hero.wireless.web.dao.business.IAgentLimitDAO;
import com.hero.wireless.web.entity.business.ext.AgentLimitExt;

import java.util.List;

public interface IAgentLimitExtDAO extends IAgentLimitDAO<AgentLimitExt> {
	List<AgentLimitExt> selectBindRoleLimitByRoleId(Integer rId);
}
