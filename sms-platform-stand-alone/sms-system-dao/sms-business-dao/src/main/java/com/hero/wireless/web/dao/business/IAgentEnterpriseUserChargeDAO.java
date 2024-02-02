package com.hero.wireless.web.dao.business;

import com.hero.wireless.web.dao.base.IDao;
import com.hero.wireless.web.entity.business.AgentEnterpriseUserCharge;
import com.hero.wireless.web.entity.business.AgentEnterpriseUserChargeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IAgentEnterpriseUserChargeDAO<T extends AgentEnterpriseUserCharge> extends IDao<AgentEnterpriseUserCharge, AgentEnterpriseUserChargeExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int countByExample(AgentEnterpriseUserChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int deleteByExample(AgentEnterpriseUserChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int insert(AgentEnterpriseUserCharge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int insertList(List<AgentEnterpriseUserCharge> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int insertSelective(AgentEnterpriseUserCharge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    List<AgentEnterpriseUserCharge> selectByExample(AgentEnterpriseUserChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    AgentEnterpriseUserCharge selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int updateByExampleSelective(@Param("record") AgentEnterpriseUserCharge record, @Param("example") AgentEnterpriseUserChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int updateByExample(@Param("record") AgentEnterpriseUserCharge record, @Param("example") AgentEnterpriseUserChargeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int updateByPrimaryKeySelective(AgentEnterpriseUserCharge record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_enterprise_user_charge
     *
     * @mbg.generated Tue Oct 19 10:29:25 CST 2021
     */
    int updateByPrimaryKey(AgentEnterpriseUserCharge record);
}