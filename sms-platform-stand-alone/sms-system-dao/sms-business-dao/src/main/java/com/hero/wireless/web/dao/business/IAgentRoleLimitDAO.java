package com.hero.wireless.web.dao.business;

import com.hero.wireless.web.dao.base.IDao;
import com.hero.wireless.web.entity.business.AgentRoleLimit;
import com.hero.wireless.web.entity.business.AgentRoleLimitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IAgentRoleLimitDAO<T extends AgentRoleLimit> extends IDao<AgentRoleLimit, AgentRoleLimitExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int countByExample(AgentRoleLimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int deleteByExample(AgentRoleLimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int insert(AgentRoleLimit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int insertList(List<AgentRoleLimit> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int insertSelective(AgentRoleLimit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    List<AgentRoleLimit> selectByExample(AgentRoleLimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    AgentRoleLimit selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int updateByExampleSelective(@Param("record") AgentRoleLimit record, @Param("example") AgentRoleLimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int updateByExample(@Param("record") AgentRoleLimit record, @Param("example") AgentRoleLimitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int updateByPrimaryKeySelective(AgentRoleLimit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_role_limit
     *
     * @mbg.generated Tue Oct 19 10:30:20 CST 2021
     */
    int updateByPrimaryKey(AgentRoleLimit record);
}