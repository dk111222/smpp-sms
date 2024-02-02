package com.hero.wireless.web.dao.ibatis.business.ext;

import com.hero.wireless.web.dao.business.ext.IAgentLimitExtDAO;
import com.hero.wireless.web.dao.ibatis.MybatisBaseBusinessExtDao;
import com.hero.wireless.web.entity.business.AgentLimit;
import com.hero.wireless.web.entity.business.AgentLimitExample;
import com.hero.wireless.web.entity.business.ext.AgentLimitExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("agentLimitExtDAO")
public class AgentLimitExtDAOImpl
		extends MybatisBaseBusinessExtDao<AgentLimitExt, AgentLimitExample, AgentLimit>
		implements IAgentLimitExtDAO {

	@Override
	public List<AgentLimitExt> selectBindRoleLimitByRoleId(Integer rId) {
		List<AgentLimitExt> list = getSqlBusinessSessionTemplate()
				.selectList("selectBindRoleLimitByRoleId", rId);
		return list;
	}

}
