package com.hero.wireless.web.dao.business;

import com.hero.wireless.web.dao.base.IDao;
import com.hero.wireless.web.entity.business.BlackList;
import com.hero.wireless.web.entity.business.BlackListExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBlackListDAO<T extends BlackList> extends IDao<BlackList, BlackListExample> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int countByExample(BlackListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int deleteByExample(BlackListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int insert(BlackList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int insertList(List<BlackList> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int insertSelective(BlackList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    List<BlackList> selectByExample(BlackListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    BlackList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int updateByExampleSelective(@Param("record") BlackList record, @Param("example") BlackListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int updateByExample(@Param("record") BlackList record, @Param("example") BlackListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int updateByPrimaryKeySelective(BlackList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table black_list
     *
     * @mbg.generated Mon Sep 30 15:38:24 CST 2019
     */
    int updateByPrimaryKey(BlackList record);
}