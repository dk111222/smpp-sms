package com.hero.wireless.web.dao.business;

import com.hero.wireless.web.dao.base.IDao;
import com.hero.wireless.web.entity.business.EnterpriseUserFee;
import com.hero.wireless.web.entity.business.EnterpriseUserFeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEnterpriseUserFeeDAO<T extends EnterpriseUserFee> extends IDao<EnterpriseUserFee, EnterpriseUserFeeExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int countByExample(EnterpriseUserFeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int deleteByExample(EnterpriseUserFeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int insert(EnterpriseUserFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int insertList(List<EnterpriseUserFee> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int insertSelective(EnterpriseUserFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    List<EnterpriseUserFee> selectByExample(EnterpriseUserFeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    EnterpriseUserFee selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int updateByExampleSelective(@Param("record") EnterpriseUserFee record, @Param("example") EnterpriseUserFeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int updateByExample(@Param("record") EnterpriseUserFee record, @Param("example") EnterpriseUserFeeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int updateByPrimaryKeySelective(EnterpriseUserFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise_user_fee
     *
     * @mbg.generated Sat Sep 14 11:02:15 CST 2019
     */
    int updateByPrimaryKey(EnterpriseUserFee record);
}