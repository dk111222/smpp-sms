package com.hero.wireless.web.dao.ibatis.business.ext;

import com.hero.wireless.web.dao.business.ext.IAgentEnterpriseUserChargeExtDAO;
import com.hero.wireless.web.dao.ibatis.MybatisBaseBusinessExtDao;
import com.hero.wireless.web.entity.business.AgentEnterpriseUserCharge;
import com.hero.wireless.web.entity.business.AgentEnterpriseUserChargeExample;
import com.hero.wireless.web.entity.business.ext.AgentEnterpriseUserChargeExt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("agentEnterpriseUserChargeExtDAO")
public class AgentEnterpriseUserChargeExtDAOImpl extends MybatisBaseBusinessExtDao<AgentEnterpriseUserChargeExt, AgentEnterpriseUserChargeExample, AgentEnterpriseUserCharge> implements
        IAgentEnterpriseUserChargeExtDAO {

    @Override
    public List<Map<String, Object>> getAgentEnterpriseUserChargeListForExport(AgentEnterpriseUserChargeExample example) {
        return getSqlBusinessSessionTemplate().selectList("getAgentEnterpriseUserChargeListForExport",example);
    }
}
