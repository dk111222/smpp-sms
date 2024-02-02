package com.hero.wireless.web.dao.ibatis.business.ext;

import com.hero.wireless.web.dao.business.ext.IAgentChargeExtDAO;
import com.hero.wireless.web.dao.ibatis.MybatisBaseBusinessExtDao;
import com.hero.wireless.web.entity.business.AgentCharge;
import com.hero.wireless.web.entity.business.AgentChargeExample;
import com.hero.wireless.web.entity.business.ext.AgentChargeExt;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("agentChargeExtDAO")
public class AgentChargeExtDAOImpl extends MybatisBaseBusinessExtDao<AgentChargeExt, AgentChargeExample, AgentCharge> implements
        IAgentChargeExtDAO {

    @Override
    public List<Map<String, Object>> getAgentChargeOrderListForExport(AgentChargeExample example) {
        return getSqlBusinessSessionTemplate().selectList("getAgentChargeOrderListForExport",example);
    }
}
