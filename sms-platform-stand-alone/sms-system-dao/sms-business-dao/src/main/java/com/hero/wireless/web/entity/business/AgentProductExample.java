package com.hero.wireless.web.entity.business;

import com.hero.wireless.web.entity.base.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentProductExample extends BaseExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    protected String dataLock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public AgentProductExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public void setDataLock(String dataLock) {
        this.dataLock = dataLock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public String getDataLock() {
        return dataLock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
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
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
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

        public Criteria andAgent_NoIsNull() {
            addCriterion("Agent_No is null");
            return (Criteria) this;
        }

        public Criteria andAgent_NoIsNotNull() {
            addCriterion("Agent_No is not null");
            return (Criteria) this;
        }

        public Criteria andAgent_NoEqualTo(Integer value) {
            addCriterion("Agent_No =", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoNotEqualTo(Integer value) {
            addCriterion("Agent_No <>", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoGreaterThan(Integer value) {
            addCriterion("Agent_No >", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Agent_No >=", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoLessThan(Integer value) {
            addCriterion("Agent_No <", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoLessThanOrEqualTo(Integer value) {
            addCriterion("Agent_No <=", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoIn(List<Integer> values) {
            addCriterion("Agent_No in", values, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoNotIn(List<Integer> values) {
            addCriterion("Agent_No not in", values, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoBetween(Integer value1, Integer value2) {
            addCriterion("Agent_No between", value1, value2, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoNotBetween(Integer value1, Integer value2) {
            addCriterion("Agent_No not between", value1, value2, "agent_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoIsNull() {
            addCriterion("Product_No is null");
            return (Criteria) this;
        }

        public Criteria andProduct_NoIsNotNull() {
            addCriterion("Product_No is not null");
            return (Criteria) this;
        }

        public Criteria andProduct_NoEqualTo(Integer value) {
            addCriterion("Product_No =", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoNotEqualTo(Integer value) {
            addCriterion("Product_No <>", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoGreaterThan(Integer value) {
            addCriterion("Product_No >", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoGreaterThanOrEqualTo(Integer value) {
            addCriterion("Product_No >=", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoLessThan(Integer value) {
            addCriterion("Product_No <", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoLessThanOrEqualTo(Integer value) {
            addCriterion("Product_No <=", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoIn(List<Integer> values) {
            addCriterion("Product_No in", values, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoNotIn(List<Integer> values) {
            addCriterion("Product_No not in", values, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoBetween(Integer value1, Integer value2) {
            addCriterion("Product_No between", value1, value2, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoNotBetween(Integer value1, Integer value2) {
            addCriterion("Product_No not between", value1, value2, "product_No");
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

        public Criteria andCreate_UserIsNull() {
            addCriterion("Create_User is null");
            return (Criteria) this;
        }

        public Criteria andCreate_UserIsNotNull() {
            addCriterion("Create_User is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_UserEqualTo(String value) {
            addCriterion("Create_User =", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserNotEqualTo(String value) {
            addCriterion("Create_User <>", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserGreaterThan(String value) {
            addCriterion("Create_User >", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserGreaterThanOrEqualTo(String value) {
            addCriterion("Create_User >=", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserLessThan(String value) {
            addCriterion("Create_User <", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserLessThanOrEqualTo(String value) {
            addCriterion("Create_User <=", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserLike(String value) {
            addCriterion("Create_User like", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserNotLike(String value) {
            addCriterion("Create_User not like", value, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserIn(List<String> values) {
            addCriterion("Create_User in", values, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserNotIn(List<String> values) {
            addCriterion("Create_User not in", values, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserBetween(String value1, String value2) {
            addCriterion("Create_User between", value1, value2, "create_User");
            return (Criteria) this;
        }

        public Criteria andCreate_UserNotBetween(String value1, String value2) {
            addCriterion("Create_User not between", value1, value2, "create_User");
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
     * This class corresponds to the database table agent_product
     *
     * @mbg.generated do_not_delete_during_merge Tue Oct 19 10:30:00 CST 2021
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table agent_product
     *
     * @mbg.generated Tue Oct 19 10:30:00 CST 2021
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