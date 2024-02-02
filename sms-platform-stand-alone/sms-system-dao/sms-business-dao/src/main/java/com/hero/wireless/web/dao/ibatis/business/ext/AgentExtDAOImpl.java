package com.hero.wireless.web.dao.ibatis.business.ext;

import com.hero.wireless.web.dao.business.ext.IAgentExtDAO;
import com.hero.wireless.web.dao.ibatis.MybatisBaseBusinessExtDao;
import com.hero.wireless.web.entity.business.Agent;
import com.hero.wireless.web.entity.business.AgentExample;
import com.hero.wireless.web.entity.business.ext.AgentExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("agentExtDAO")
public class AgentExtDAOImpl
		extends MybatisBaseBusinessExtDao<AgentExt, AgentExample, Agent>
		implements IAgentExtDAO {

	public List<AgentExt> selectRolesAndLimitsByAgentExample(AgentExample example) {
		List<AgentExt> list = getSqlBusinessSessionTemplate()
				.selectList("selectRolesAndLimitsByAgentExample", example);
		return list;
	}

}
