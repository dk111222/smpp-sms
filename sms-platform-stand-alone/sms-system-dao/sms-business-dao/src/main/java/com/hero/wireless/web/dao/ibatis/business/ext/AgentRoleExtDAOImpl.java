package com.hero.wireless.web.dao.ibatis.business.ext;

import com.hero.wireless.web.dao.business.ext.IAgentRoleExtDAO;
import com.hero.wireless.web.dao.ibatis.MybatisBaseBusinessExtDao;
import com.hero.wireless.web.entity.business.AgentRole;
import com.hero.wireless.web.entity.business.AgentRoleExample;
import com.hero.wireless.web.entity.business.ext.AgentRoleExt;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("agentRoleExtDAO")
public class AgentRoleExtDAOImpl extends
        MybatisBaseBusinessExtDao<AgentRoleExt, AgentRoleExample, AgentRole> implements IAgentRoleExtDAO {

	@Override
	public List<AgentRoleExt> selectBindAgentRoleByExample(Integer uId) {
		List<AgentRoleExt> list = getSqlBusinessSessionTemplate()
				.selectList("selectBindAgentRoleByExample", uId);
		return list;
	}

}
