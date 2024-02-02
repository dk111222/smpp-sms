package com.hero.wireless.web.dao.business.ext;

import com.hero.wireless.web.dao.business.IAgentChargeDAO;
import com.hero.wireless.web.dao.ext.IExtDAO;
import com.hero.wireless.web.entity.business.AgentChargeExample;
import com.hero.wireless.web.entity.business.ext.AgentChargeExt;

import java.util.List;
import java.util.Map;

public interface IAgentChargeExtDAO extends IAgentChargeDAO<AgentChargeExt>, IExtDAO<AgentChargeExt, AgentChargeExample> {

    List<Map<String, Object>> getAgentChargeOrderListForExport(AgentChargeExample example);
}
