package com.hero.wireless.web.dao.business;

import com.hero.wireless.web.dao.base.IDao;
import com.hero.wireless.web.entity.business.ChannelDiversion;
import com.hero.wireless.web.entity.business.ChannelDiversionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IChannelDiversionDAO<T extends ChannelDiversion> extends IDao<ChannelDiversion, ChannelDiversionExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int countByExample(ChannelDiversionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int deleteByExample(ChannelDiversionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int insert(ChannelDiversion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int insertList(List<ChannelDiversion> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int insertSelective(ChannelDiversion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    List<ChannelDiversion> selectByExample(ChannelDiversionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    ChannelDiversion selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int updateByExampleSelective(@Param("record") ChannelDiversion record, @Param("example") ChannelDiversionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int updateByExample(@Param("record") ChannelDiversion record, @Param("example") ChannelDiversionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int updateByPrimaryKeySelective(ChannelDiversion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_diversion
     *
     * @mbg.generated Thu Jul 22 17:24:50 CST 2021
     */
    int updateByPrimaryKey(ChannelDiversion record);
}