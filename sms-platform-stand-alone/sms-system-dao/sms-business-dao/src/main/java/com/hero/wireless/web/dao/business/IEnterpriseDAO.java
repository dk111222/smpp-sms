package com.hero.wireless.web.dao.business;

import com.hero.wireless.web.dao.base.IDao;
import com.hero.wireless.web.entity.business.Enterprise;
import com.hero.wireless.web.entity.business.EnterpriseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEnterpriseDAO<T extends Enterprise> extends IDao<Enterprise, EnterpriseExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int countByExample(EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int deleteByExample(EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int insert(Enterprise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int insertList(List<Enterprise> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int insertSelective(Enterprise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    List<Enterprise> selectByExample(EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    Enterprise selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int updateByExampleSelective(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int updateByExample(@Param("record") Enterprise record, @Param("example") EnterpriseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int updateByPrimaryKeySelective(Enterprise record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table enterprise
     *
     * @mbg.generated Wed Mar 25 14:14:46 CST 2020
     */
    int updateByPrimaryKey(Enterprise record);
}