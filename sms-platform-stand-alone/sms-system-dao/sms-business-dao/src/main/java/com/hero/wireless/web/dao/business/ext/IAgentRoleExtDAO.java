package com.hero.wireless.web.dao.business.ext;

import com.hero.wireless.web.dao.business.IAgentRoleDAO;
import com.hero.wireless.web.entity.business.ext.AgentRoleExt;

import java.util.List;

public interface IAgentRoleExtDAO extends IAgentRoleDAO<AgentRoleExt> {
	List<AgentRoleExt> selectBindAgentRoleByExample(Integer uId);
}
