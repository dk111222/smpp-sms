package com.hero.wireless.web.entity.business;

import com.hero.wireless.web.entity.base.BaseExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsRouteExample extends BaseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    protected String dataLock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public SmsRouteExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public void setDataLock(String dataLock) {
        this.dataLock = dataLock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public String getDataLock() {
        return dataLock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        public void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        public void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        public void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberIsNull() {
            addCriterion("Prefix_Number is null");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberIsNotNull() {
            addCriterion("Prefix_Number is not null");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberEqualTo(String value) {
            addCriterion("Prefix_Number =", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberNotEqualTo(String value) {
            addCriterion("Prefix_Number <>", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberGreaterThan(String value) {
            addCriterion("Prefix_Number >", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberGreaterThanOrEqualTo(String value) {
            addCriterion("Prefix_Number >=", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberLessThan(String value) {
            addCriterion("Prefix_Number <", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberLessThanOrEqualTo(String value) {
            addCriterion("Prefix_Number <=", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberLike(String value) {
            addCriterion("Prefix_Number like", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberNotLike(String value) {
            addCriterion("Prefix_Number not like", value, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberIn(List<String> values) {
            addCriterion("Prefix_Number in", values, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberNotIn(List<String> values) {
            addCriterion("Prefix_Number not in", values, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberBetween(String value1, String value2) {
            addCriterion("Prefix_Number between", value1, value2, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andPrefix_NumberNotBetween(String value1, String value2) {
            addCriterion("Prefix_Number not between", value1, value2, "prefix_Number");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeIsNull() {
            addCriterion("Route_Name_Code is null");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeIsNotNull() {
            addCriterion("Route_Name_Code is not null");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeEqualTo(String value) {
            addCriterion("Route_Name_Code =", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeNotEqualTo(String value) {
            addCriterion("Route_Name_Code <>", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeGreaterThan(String value) {
            addCriterion("Route_Name_Code >", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeGreaterThanOrEqualTo(String value) {
            addCriterion("Route_Name_Code >=", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeLessThan(String value) {
            addCriterion("Route_Name_Code <", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeLessThanOrEqualTo(String value) {
            addCriterion("Route_Name_Code <=", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeLike(String value) {
            addCriterion("Route_Name_Code like", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeNotLike(String value) {
            addCriterion("Route_Name_Code not like", value, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeIn(List<String> values) {
            addCriterion("Route_Name_Code in", values, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeNotIn(List<String> values) {
            addCriterion("Route_Name_Code not in", values, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeBetween(String value1, String value2) {
            addCriterion("Route_Name_Code between", value1, value2, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andRoute_Name_CodeNotBetween(String value1, String value2) {
            addCriterion("Route_Name_Code not between", value1, value2, "route_Name_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeIsNull() {
            addCriterion("Country_Code is null");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeIsNotNull() {
            addCriterion("Country_Code is not null");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeEqualTo(String value) {
            addCriterion("Country_Code =", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeNotEqualTo(String value) {
            addCriterion("Country_Code <>", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeGreaterThan(String value) {
            addCriterion("Country_Code >", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeGreaterThanOrEqualTo(String value) {
            addCriterion("Country_Code >=", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeLessThan(String value) {
            addCriterion("Country_Code <", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeLessThanOrEqualTo(String value) {
            addCriterion("Country_Code <=", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeLike(String value) {
            addCriterion("Country_Code like", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeNotLike(String value) {
            addCriterion("Country_Code not like", value, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeIn(List<String> values) {
            addCriterion("Country_Code in", values, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeNotIn(List<String> values) {
            addCriterion("Country_Code not in", values, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeBetween(String value1, String value2) {
            addCriterion("Country_Code between", value1, value2, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_CodeNotBetween(String value1, String value2) {
            addCriterion("Country_Code not between", value1, value2, "country_Code");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberIsNull() {
            addCriterion("Country_Number is null");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberIsNotNull() {
            addCriterion("Country_Number is not null");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberEqualTo(String value) {
            addCriterion("Country_Number =", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberNotEqualTo(String value) {
            addCriterion("Country_Number <>", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberGreaterThan(String value) {
            addCriterion("Country_Number >", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberGreaterThanOrEqualTo(String value) {
            addCriterion("Country_Number >=", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberLessThan(String value) {
            addCriterion("Country_Number <", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberLessThanOrEqualTo(String value) {
            addCriterion("Country_Number <=", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberLike(String value) {
            addCriterion("Country_Number like", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberNotLike(String value) {
            addCriterion("Country_Number not like", value, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberIn(List<String> values) {
            addCriterion("Country_Number in", values, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberNotIn(List<String> values) {
            addCriterion("Country_Number not in", values, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberBetween(String value1, String value2) {
            addCriterion("Country_Number between", value1, value2, "country_Number");
            return (Criteria) this;
        }

        public Criteria andCountry_NumberNotBetween(String value1, String value2) {
            addCriterion("Country_Number not between", value1, value2, "country_Number");
            return (Criteria) this;
        }

        public Criteria andMCCIsNull() {
            addCriterion("MCC is null");
            return (Criteria) this;
        }

        public Criteria andMCCIsNotNull() {
            addCriterion("MCC is not null");
            return (Criteria) this;
        }

        public Criteria andMCCEqualTo(String value) {
            addCriterion("MCC =", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCNotEqualTo(String value) {
            addCriterion("MCC <>", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCGreaterThan(String value) {
            addCriterion("MCC >", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCGreaterThanOrEqualTo(String value) {
            addCriterion("MCC >=", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCLessThan(String value) {
            addCriterion("MCC <", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCLessThanOrEqualTo(String value) {
            addCriterion("MCC <=", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCLike(String value) {
            addCriterion("MCC like", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCNotLike(String value) {
            addCriterion("MCC not like", value, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCIn(List<String> values) {
            addCriterion("MCC in", values, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCNotIn(List<String> values) {
            addCriterion("MCC not in", values, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCBetween(String value1, String value2) {
            addCriterion("MCC between", value1, value2, "MCC");
            return (Criteria) this;
        }

        public Criteria andMCCNotBetween(String value1, String value2) {
            addCriterion("MCC not between", value1, value2, "MCC");
            return (Criteria) this;
        }

        public Criteria andMNCIsNull() {
            addCriterion("MNC is null");
            return (Criteria) this;
        }

        public Criteria andMNCIsNotNull() {
            addCriterion("MNC is not null");
            return (Criteria) this;
        }

        public Criteria andMNCEqualTo(String value) {
            addCriterion("MNC =", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCNotEqualTo(String value) {
            addCriterion("MNC <>", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCGreaterThan(String value) {
            addCriterion("MNC >", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCGreaterThanOrEqualTo(String value) {
            addCriterion("MNC >=", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCLessThan(String value) {
            addCriterion("MNC <", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCLessThanOrEqualTo(String value) {
            addCriterion("MNC <=", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCLike(String value) {
            addCriterion("MNC like", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCNotLike(String value) {
            addCriterion("MNC not like", value, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCIn(List<String> values) {
            addCriterion("MNC in", values, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCNotIn(List<String> values) {
            addCriterion("MNC not in", values, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCBetween(String value1, String value2) {
            addCriterion("MNC between", value1, value2, "MNC");
            return (Criteria) this;
        }

        public Criteria andMNCNotBetween(String value1, String value2) {
            addCriterion("MNC not between", value1, value2, "MNC");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("Description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("Description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("Description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("Description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("Description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("Description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("Description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("Description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("Description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("Description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("Description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("Description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("Description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("Description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreate_DateIsNull() {
            addCriterion("Create_Date is null");
            return (Criteria) this;
        }

        public Criteria andCreate_DateIsNotNull() {
            addCriterion("Create_Date is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_DateEqualTo(Date value) {
            addCriterion("Create_Date =", value, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateNotEqualTo(Date value) {
            addCriterion("Create_Date <>", value, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateGreaterThan(Date value) {
            addCriterion("Create_Date >", value, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateGreaterThanOrEqualTo(Date value) {
            addCriterion("Create_Date >=", value, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateLessThan(Date value) {
            addCriterion("Create_Date <", value, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateLessThanOrEqualTo(Date value) {
            addCriterion("Create_Date <=", value, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateIn(List<Date> values) {
            addCriterion("Create_Date in", values, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateNotIn(List<Date> values) {
            addCriterion("Create_Date not in", values, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateBetween(Date value1, Date value2) {
            addCriterion("Create_Date between", value1, value2, "create_Date");
            return (Criteria) this;
        }

        public Criteria andCreate_DateNotBetween(Date value1, Date value2) {
            addCriterion("Create_Date not between", value1, value2, "create_Date");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sms_route
     *
     * @mbg.generated do_not_delete_during_merge Thu Aug 06 18:06:31 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table sms_route
     *
     * @mbg.generated Thu Aug 06 18:06:31 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}