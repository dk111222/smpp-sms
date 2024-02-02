package com.hero.wireless.web.dao.business.ext;


import com.hero.wireless.web.dao.business.IAgentDAO;
import com.hero.wireless.web.entity.business.AgentExample;
import com.hero.wireless.web.entity.business.ext.AgentExt;

import java.util.List;

public interface IAgentExtDAO extends IAgentDAO<AgentExt> {
	List<AgentExt> selectRolesAndLimitsByAgentExample(AgentExample example);
}
