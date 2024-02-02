package com.hero.wireless.web.dao.business.ext;

import com.hero.wireless.web.dao.business.IAgentEnterpriseUserChargeDAO;
import com.hero.wireless.web.dao.ext.IExtDAO;
import com.hero.wireless.web.entity.business.AgentEnterpriseUserChargeExample;
import com.hero.wireless.web.entity.business.ext.AgentEnterpriseUserChargeExt;

import java.util.List;
import java.util.Map;

public interface IAgentEnterpriseUserChargeExtDAO extends IAgentEnterpriseUserChargeDAO<AgentEnterpriseUserChargeExt>, IExtDAO<AgentEnterpriseUserChargeExt, AgentEnterpriseUserChargeExample> {

    List<Map<String, Object>> getAgentEnterpriseUserChargeListForExport(AgentEnterpriseUserChargeExample example);
}
