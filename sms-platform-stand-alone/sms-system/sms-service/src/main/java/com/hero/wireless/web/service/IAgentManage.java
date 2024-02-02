package com.hero.wireless.web.service;

import com.hero.wireless.web.entity.business.*;
import com.hero.wireless.web.entity.business.ext.AgentChargeExt;
import com.hero.wireless.web.entity.business.ext.AgentExt;
import com.hero.wireless.web.entity.business.ext.AgentLimitExt;
import com.hero.wireless.web.entity.business.ext.AgentRoleExt;

import java.util.List;

/**
 *
 * 代理商管理
 *
 * @author volcano
 * @date 2019年9月17日上午2:30:54
 * @version V1.0
 */
public interface IAgentManage {

	/**
	 * 登录
	 * 
	 * @param agent
	 * @return
	 */
	Agent agentLogin(Agent agent);

	/**
	 *
	 * @param agentExt
	 * @return
	 * @author volcano
	 * @date 2019年9月17日上午2:31:27
	 * @version V1.0
	 */
	List<Agent> queryAgentList(AgentExt agentExt);

	/**
	 *
	 * @param agentFee
	 * @return
	 * @author volcano
	 * @date 2019年9月17日上午3:15:14
	 * @version V1.0
	 */
	List<AgentFee> queryAgentFeeList(AgentFee agentFee);

	/**
	 * 删除代理商资费
	 * 
	 * @param ckIds
	 */
	void delAgentFee(List<Integer> ckIds);

	/**
	 * 添加代理商资费
	 * 
	 * @param agentFee
	 */
	void addAgentFee(AgentFee agentFee);

	/**
	 * 修改代理商资费
	 * 
	 * @param agentFee
	 */
	void editAgentFee(AgentFee agentFee);

	/**
	 *  代理商充值
	 * 
	 * @param agentCharge
	 */
	void charge(AgentCharge agentCharge);

	/**
	 * 代理充值列表
	 * @param agentChargeExt
	 * @return
	 */
	List<AgentCharge> queryAgentChargeList(AgentChargeExt agentChargeExt);

	/**
	 * 增加代理
	 *
	 * @param data
	 * @return
	 */
	Agent addAgent(Agent data);

	/**
	 * 修改代理商
	 * @param agentExt
	 * @return
	 */
	Integer editAgent(AgentExt agentExt);

	/**
	 * 修改密码
	 * @param agent
	 * @param oldPassword
	 * @return
	 */
	Agent editPassword(Agent agent, String oldPassword);

	/**
	 * 修改代理商状态
	 * @param uIdList
	 * @param status
	 * @return
	 */
	Integer updateAgentStatus(List<Integer> uIdList, String status);

	/**
	 * 查询代理权限
	 * 
	 * @param agentLimit
	 * @return
	 */
	List<AgentLimit> queryAgentLimitList(AgentLimit agentLimit);

	/**
	 * 添加代理商权限
	 * 
	 * @param agentLimit
	 */
	void addAgentLimit(AgentLimit agentLimit);

	/**
	 * 代理商角色
	 * @param agentRole
	 * @return
	 */
	List<AgentRole> queryAgentRoleList(AgentRole agentRole);

	/**
	 * 添加代理商角色
	 * 
	 * @param agentRole
	 * @return
	 */
	AgentRole addAgentRole(AgentRole agentRole);

	/**
	 * 代理商角色绑定权限
	 * 
	 * @param ckIds
	 * @return
	 */
	List<AgentLimitExt> queryBindRoleLimitList(List<Integer> ckIds);

	/**
	 * 查询代理商角色列表
	 * @param ckIds
	 * @return
	 */
	List<AgentRoleExt> queryBindAgentRoleList(List<Integer> ckIds);

	/**
	 * 绑定角色权限
	 * @param roleLimitList
	 */
	void bindRoleLimit(List<AgentRoleLimit> roleLimitList);

	/**
	 * 代理商角色列表
	 * 
	 * @param agentRolesList
	 * @return
	 */
	boolean bindAgentRoles(List<AgentRoles> agentRolesList);

	/**
	 * 代理商权限
	 * @param agentLimit
	 * @return
	 */
	Integer editLimit(AgentLimit agentLimit);

	/**
	 * 代理商角色
	 * 
	 * @param agentRole
	 */
	void editRole(AgentRole agentRole);

	/**
	 * 删除代理商权限
	 *			删除所选权限以及下级权限
	 * @param ckIds
	 */
	int deleteAgentLimit(List<Integer> ckIds);

	/**
	 * 获取当天新增的代理商数据
	 * @return
	 */
    List<Agent> queryAddAgentCount();
}
