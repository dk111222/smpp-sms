package com.hero.wireless.web.entity.send;

import com.hero.wireless.web.entity.base.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputAwaitExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected String dataLock;

    protected List<Criteria> oredCriteria;

    public InputAwaitExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDataLock(String dataLock) {
        this.dataLock = dataLock;
    }

    public String getDataLock() {
        return dataLock;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUUIDIsNull() {
            addCriterion("UUID is null");
            return (Criteria) this;
        }

        public Criteria andUUIDIsNotNull() {
            addCriterion("UUID is not null");
            return (Criteria) this;
        }

        public Criteria andUUIDEqualTo(String value) {
            addCriterion("UUID =", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDNotEqualTo(String value) {
            addCriterion("UUID <>", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDGreaterThan(String value) {
            addCriterion("UUID >", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDGreaterThanOrEqualTo(String value) {
            addCriterion("UUID >=", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDLessThan(String value) {
            addCriterion("UUID <", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDLessThanOrEqualTo(String value) {
            addCriterion("UUID <=", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDLike(String value) {
            addCriterion("UUID like", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDNotLike(String value) {
            addCriterion("UUID not like", value, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDIn(List<String> values) {
            addCriterion("UUID in", values, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDNotIn(List<String> values) {
            addCriterion("UUID not in", values, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDBetween(String value1, String value2) {
            addCriterion("UUID between", value1, value2, "UUID");
            return (Criteria) this;
        }

        public Criteria andUUIDNotBetween(String value1, String value2) {
            addCriterion("UUID not between", value1, value2, "UUID");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoIsNull() {
            addCriterion("Msg_Batch_No is null");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoIsNotNull() {
            addCriterion("Msg_Batch_No is not null");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoEqualTo(String value) {
            addCriterion("Msg_Batch_No =", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoNotEqualTo(String value) {
            addCriterion("Msg_Batch_No <>", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoGreaterThan(String value) {
            addCriterion("Msg_Batch_No >", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoGreaterThanOrEqualTo(String value) {
            addCriterion("Msg_Batch_No >=", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoLessThan(String value) {
            addCriterion("Msg_Batch_No <", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoLessThanOrEqualTo(String value) {
            addCriterion("Msg_Batch_No <=", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoLike(String value) {
            addCriterion("Msg_Batch_No like", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoNotLike(String value) {
            addCriterion("Msg_Batch_No not like", value, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoIn(List<String> values) {
            addCriterion("Msg_Batch_No in", values, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoNotIn(List<String> values) {
            addCriterion("Msg_Batch_No not in", values, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoBetween(String value1, String value2) {
            addCriterion("Msg_Batch_No between", value1, value2, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andMsg_Batch_NoNotBetween(String value1, String value2) {
            addCriterion("Msg_Batch_No not between", value1, value2, "msg_Batch_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdIsNull() {
            addCriterion("Enterprise_Msg_Id is null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdIsNotNull() {
            addCriterion("Enterprise_Msg_Id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdEqualTo(String value) {
            addCriterion("Enterprise_Msg_Id =", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdNotEqualTo(String value) {
            addCriterion("Enterprise_Msg_Id <>", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdGreaterThan(String value) {
            addCriterion("Enterprise_Msg_Id >", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdGreaterThanOrEqualTo(String value) {
            addCriterion("Enterprise_Msg_Id >=", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdLessThan(String value) {
            addCriterion("Enterprise_Msg_Id <", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdLessThanOrEqualTo(String value) {
            addCriterion("Enterprise_Msg_Id <=", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdLike(String value) {
            addCriterion("Enterprise_Msg_Id like", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdNotLike(String value) {
            addCriterion("Enterprise_Msg_Id not like", value, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdIn(List<String> values) {
            addCriterion("Enterprise_Msg_Id in", values, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdNotIn(List<String> values) {
            addCriterion("Enterprise_Msg_Id not in", values, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdBetween(String value1, String value2) {
            addCriterion("Enterprise_Msg_Id between", value1, value2, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_Msg_IdNotBetween(String value1, String value2) {
            addCriterion("Enterprise_Msg_Id not between", value1, value2, "enterprise_Msg_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoIsNull() {
            addCriterion("Enterprise_No is null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoIsNotNull() {
            addCriterion("Enterprise_No is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoEqualTo(String value) {
            addCriterion("Enterprise_No =", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoNotEqualTo(String value) {
            addCriterion("Enterprise_No <>", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoGreaterThan(String value) {
            addCriterion("Enterprise_No >", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoGreaterThanOrEqualTo(String value) {
            addCriterion("Enterprise_No >=", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoLessThan(String value) {
            addCriterion("Enterprise_No <", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoLessThanOrEqualTo(String value) {
            addCriterion("Enterprise_No <=", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoLike(String value) {
            addCriterion("Enterprise_No like", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoNotLike(String value) {
            addCriterion("Enterprise_No not like", value, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoIn(List<String> values) {
            addCriterion("Enterprise_No in", values, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoNotIn(List<String> values) {
            addCriterion("Enterprise_No not in", values, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoBetween(String value1, String value2) {
            addCriterion("Enterprise_No between", value1, value2, "enterprise_No");
            return (Criteria) this;
        }

        public Criteria andEnterprise_NoNotBetween(String value1, String value2) {
            addCriterion("Enterprise_No not between", value1, value2, "enterprise_No");
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

        public Criteria andAgent_NoEqualTo(String value) {
            addCriterion("Agent_No =", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoNotEqualTo(String value) {
            addCriterion("Agent_No <>", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoGreaterThan(String value) {
            addCriterion("Agent_No >", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoGreaterThanOrEqualTo(String value) {
            addCriterion("Agent_No >=", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoLessThan(String value) {
            addCriterion("Agent_No <", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoLessThanOrEqualTo(String value) {
            addCriterion("Agent_No <=", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoLike(String value) {
            addCriterion("Agent_No like", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoNotLike(String value) {
            addCriterion("Agent_No not like", value, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoIn(List<String> values) {
            addCriterion("Agent_No in", values, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoNotIn(List<String> values) {
            addCriterion("Agent_No not in", values, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoBetween(String value1, String value2) {
            addCriterion("Agent_No between", value1, value2, "agent_No");
            return (Criteria) this;
        }

        public Criteria andAgent_NoNotBetween(String value1, String value2) {
            addCriterion("Agent_No not between", value1, value2, "agent_No");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdIsNull() {
            addCriterion("Business_User_Id is null");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdIsNotNull() {
            addCriterion("Business_User_Id is not null");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdEqualTo(Integer value) {
            addCriterion("Business_User_Id =", value, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdNotEqualTo(Integer value) {
            addCriterion("Business_User_Id <>", value, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdGreaterThan(Integer value) {
            addCriterion("Business_User_Id >", value, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Business_User_Id >=", value, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdLessThan(Integer value) {
            addCriterion("Business_User_Id <", value, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdLessThanOrEqualTo(Integer value) {
            addCriterion("Business_User_Id <=", value, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdIn(List<Integer> values) {
            addCriterion("Business_User_Id in", values, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdNotIn(List<Integer> values) {
            addCriterion("Business_User_Id not in", values, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdBetween(Integer value1, Integer value2) {
            addCriterion("Business_User_Id between", value1, value2, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andBusiness_User_IdNotBetween(Integer value1, Integer value2) {
            addCriterion("Business_User_Id not between", value1, value2, "business_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdIsNull() {
            addCriterion("Enterprise_User_Id is null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdIsNotNull() {
            addCriterion("Enterprise_User_Id is not null");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdEqualTo(Integer value) {
            addCriterion("Enterprise_User_Id =", value, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdNotEqualTo(Integer value) {
            addCriterion("Enterprise_User_Id <>", value, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdGreaterThan(Integer value) {
            addCriterion("Enterprise_User_Id >", value, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Enterprise_User_Id >=", value, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdLessThan(Integer value) {
            addCriterion("Enterprise_User_Id <", value, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdLessThanOrEqualTo(Integer value) {
            addCriterion("Enterprise_User_Id <=", value, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdIn(List<Integer> values) {
            addCriterion("Enterprise_User_Id in", values, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdNotIn(List<Integer> values) {
            addCriterion("Enterprise_User_Id not in", values, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdBetween(Integer value1, Integer value2) {
            addCriterion("Enterprise_User_Id between", value1, value2, "enterprise_User_Id");
            return (Criteria) this;
        }

        public Criteria andEnterprise_User_IdNotBetween(Integer value1, Integer value2) {
            addCriterion("Enterprise_User_Id not between", value1, value2, "enterprise_User_Id");
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

        public Criteria andProduct_NoEqualTo(String value) {
            addCriterion("Product_No =", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoNotEqualTo(String value) {
            addCriterion("Product_No <>", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoGreaterThan(String value) {
            addCriterion("Product_No >", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoGreaterThanOrEqualTo(String value) {
            addCriterion("Product_No >=", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoLessThan(String value) {
            addCriterion("Product_No <", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoLessThanOrEqualTo(String value) {
            addCriterion("Product_No <=", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoLike(String value) {
            addCriterion("Product_No like", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoNotLike(String value) {
            addCriterion("Product_No not like", value, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoIn(List<String> values) {
            addCriterion("Product_No in", values, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoNotIn(List<String> values) {
            addCriterion("Product_No not in", values, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoBetween(String value1, String value2) {
            addCriterion("Product_No between", value1, value2, "product_No");
            return (Criteria) this;
        }

        public Criteria andProduct_NoNotBetween(String value1, String value2) {
            addCriterion("Product_No not between", value1, value2, "product_No");
            return (Criteria) this;
        }

        public Criteria andIs_LMSIsNull() {
            addCriterion("Is_LMS is null");
            return (Criteria) this;
        }

        public Criteria andIs_LMSIsNotNull() {
            addCriterion("Is_LMS is not null");
            return (Criteria) this;
        }

        public Criteria andIs_LMSEqualTo(Boolean value) {
            addCriterion("Is_LMS =", value, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSNotEqualTo(Boolean value) {
            addCriterion("Is_LMS <>", value, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSGreaterThan(Boolean value) {
            addCriterion("Is_LMS >", value, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSGreaterThanOrEqualTo(Boolean value) {
            addCriterion("Is_LMS >=", value, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSLessThan(Boolean value) {
            addCriterion("Is_LMS <", value, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSLessThanOrEqualTo(Boolean value) {
            addCriterion("Is_LMS <=", value, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSIn(List<Boolean> values) {
            addCriterion("Is_LMS in", values, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSNotIn(List<Boolean> values) {
            addCriterion("Is_LMS not in", values, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSBetween(Boolean value1, Boolean value2) {
            addCriterion("Is_LMS between", value1, value2, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andIs_LMSNotBetween(Boolean value1, Boolean value2) {
            addCriterion("Is_LMS not between", value1, value2, "is_LMS");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeIsNull() {
            addCriterion("Data_Status_Code is null");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeIsNotNull() {
            addCriterion("Data_Status_Code is not null");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeEqualTo(String value) {
            addCriterion("Data_Status_Code =", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeNotEqualTo(String value) {
            addCriterion("Data_Status_Code <>", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeGreaterThan(String value) {
            addCriterion("Data_Status_Code >", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeGreaterThanOrEqualTo(String value) {
            addCriterion("Data_Status_Code >=", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeLessThan(String value) {
            addCriterion("Data_Status_Code <", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeLessThanOrEqualTo(String value) {
            addCriterion("Data_Status_Code <=", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeLike(String value) {
            addCriterion("Data_Status_Code like", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeNotLike(String value) {
            addCriterion("Data_Status_Code not like", value, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeIn(List<String> values) {
            addCriterion("Data_Status_Code in", values, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeNotIn(List<String> values) {
            addCriterion("Data_Status_Code not in", values, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeBetween(String value1, String value2) {
            addCriterion("Data_Status_Code between", value1, value2, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andData_Status_CodeNotBetween(String value1, String value2) {
            addCriterion("Data_Status_Code not between", value1, value2, "data_Status_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeIsNull() {
            addCriterion("Message_Type_Code is null");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeIsNotNull() {
            addCriterion("Message_Type_Code is not null");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeEqualTo(String value) {
            addCriterion("Message_Type_Code =", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeNotEqualTo(String value) {
            addCriterion("Message_Type_Code <>", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeGreaterThan(String value) {
            addCriterion("Message_Type_Code >", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeGreaterThanOrEqualTo(String value) {
            addCriterion("Message_Type_Code >=", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeLessThan(String value) {
            addCriterion("Message_Type_Code <", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeLessThanOrEqualTo(String value) {
            addCriterion("Message_Type_Code <=", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeLike(String value) {
            addCriterion("Message_Type_Code like", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeNotLike(String value) {
            addCriterion("Message_Type_Code not like", value, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeIn(List<String> values) {
            addCriterion("Message_Type_Code in", values, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeNotIn(List<String> values) {
            addCriterion("Message_Type_Code not in", values, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeBetween(String value1, String value2) {
            addCriterion("Message_Type_Code between", value1, value2, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andMessage_Type_CodeNotBetween(String value1, String value2) {
            addCriterion("Message_Type_Code not between", value1, value2, "message_Type_Code");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("Content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("Content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("Content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("Content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("Content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("Content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("Content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("Content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("Content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("Content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("Content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("Content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("Content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("Content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andCharsetIsNull() {
            addCriterion("Charset is null");
            return (Criteria) this;
        }

        public Criteria andCharsetIsNotNull() {
            addCriterion("Charset is not null");
            return (Criteria) this;
        }

        public Criteria andCharsetEqualTo(String value) {
            addCriterion("Charset =", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetNotEqualTo(String value) {
            addCriterion("Charset <>", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetGreaterThan(String value) {
            addCriterion("Charset >", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetGreaterThanOrEqualTo(String value) {
            addCriterion("Charset >=", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetLessThan(String value) {
            addCriterion("Charset <", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetLessThanOrEqualTo(String value) {
            addCriterion("Charset <=", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetLike(String value) {
            addCriterion("Charset like", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetNotLike(String value) {
            addCriterion("Charset not like", value, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetIn(List<String> values) {
            addCriterion("Charset in", values, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetNotIn(List<String> values) {
            addCriterion("Charset not in", values, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetBetween(String value1, String value2) {
            addCriterion("Charset between", value1, value2, "charset");
            return (Criteria) this;
        }

        public Criteria andCharsetNotBetween(String value1, String value2) {
            addCriterion("Charset not between", value1, value2, "charset");
            return (Criteria) this;
        }

        public Criteria andPhone_NosIsNull() {
            addCriterion("Phone_Nos is null");
            return (Criteria) this;
        }

        public Criteria andPhone_NosIsNotNull() {
            addCriterion("Phone_Nos is not null");
            return (Criteria) this;
        }

        public Criteria andPhone_NosEqualTo(String value) {
            addCriterion("Phone_Nos =", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosNotEqualTo(String value) {
            addCriterion("Phone_Nos <>", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosGreaterThan(String value) {
            addCriterion("Phone_Nos >", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosGreaterThanOrEqualTo(String value) {
            addCriterion("Phone_Nos >=", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosLessThan(String value) {
            addCriterion("Phone_Nos <", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosLessThanOrEqualTo(String value) {
            addCriterion("Phone_Nos <=", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosLike(String value) {
            addCriterion("Phone_Nos like", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosNotLike(String value) {
            addCriterion("Phone_Nos not like", value, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosIn(List<String> values) {
            addCriterion("Phone_Nos in", values, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosNotIn(List<String> values) {
            addCriterion("Phone_Nos not in", values, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosBetween(String value1, String value2) {
            addCriterion("Phone_Nos between", value1, value2, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_NosNotBetween(String value1, String value2) {
            addCriterion("Phone_Nos not between", value1, value2, "phone_Nos");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountIsNull() {
            addCriterion("Phone_Nos_Count is null");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountIsNotNull() {
            addCriterion("Phone_Nos_Count is not null");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountEqualTo(Integer value) {
            addCriterion("Phone_Nos_Count =", value, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountNotEqualTo(Integer value) {
            addCriterion("Phone_Nos_Count <>", value, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountGreaterThan(Integer value) {
            addCriterion("Phone_Nos_Count >", value, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountGreaterThanOrEqualTo(Integer value) {
            addCriterion("Phone_Nos_Count >=", value, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountLessThan(Integer value) {
            addCriterion("Phone_Nos_Count <", value, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountLessThanOrEqualTo(Integer value) {
            addCriterion("Phone_Nos_Count <=", value, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountIn(List<Integer> values) {
            addCriterion("Phone_Nos_Count in", values, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountNotIn(List<Integer> values) {
            addCriterion("Phone_Nos_Count not in", values, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountBetween(Integer value1, Integer value2) {
            addCriterion("Phone_Nos_Count between", value1, value2, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andPhone_Nos_CountNotBetween(Integer value1, Integer value2) {
            addCriterion("Phone_Nos_Count not between", value1, value2, "phone_Nos_Count");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeIsNull() {
            addCriterion("Protocol_Type_Code is null");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeIsNotNull() {
            addCriterion("Protocol_Type_Code is not null");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeEqualTo(String value) {
            addCriterion("Protocol_Type_Code =", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeNotEqualTo(String value) {
            addCriterion("Protocol_Type_Code <>", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeGreaterThan(String value) {
            addCriterion("Protocol_Type_Code >", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeGreaterThanOrEqualTo(String value) {
            addCriterion("Protocol_Type_Code >=", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeLessThan(String value) {
            addCriterion("Protocol_Type_Code <", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeLessThanOrEqualTo(String value) {
            addCriterion("Protocol_Type_Code <=", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeLike(String value) {
            addCriterion("Protocol_Type_Code like", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeNotLike(String value) {
            addCriterion("Protocol_Type_Code not like", value, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeIn(List<String> values) {
            addCriterion("Protocol_Type_Code in", values, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeNotIn(List<String> values) {
            addCriterion("Protocol_Type_Code not in", values, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeBetween(String value1, String value2) {
            addCriterion("Protocol_Type_Code between", value1, value2, "protocol_Type_Code");
            return (Criteria) this;
        }

        public Criteria andProtocol_Type_CodeNotBetween(String value1, String value2) {
            addCriterion("Protocol_Type_Code not between", value1, value2, "protocol_Type_Code");
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

        public Criteria andSource_IPIsNull() {
            addCriterion("Source_IP is null");
            return (Criteria) this;
        }

        public Criteria andSource_IPIsNotNull() {
            addCriterion("Source_IP is not null");
            return (Criteria) this;
        }

        public Criteria andSource_IPEqualTo(String value) {
            addCriterion("Source_IP =", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPNotEqualTo(String value) {
            addCriterion("Source_IP <>", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPGreaterThan(String value) {
            addCriterion("Source_IP >", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPGreaterThanOrEqualTo(String value) {
            addCriterion("Source_IP >=", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPLessThan(String value) {
            addCriterion("Source_IP <", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPLessThanOrEqualTo(String value) {
            addCriterion("Source_IP <=", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPLike(String value) {
            addCriterion("Source_IP like", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPNotLike(String value) {
            addCriterion("Source_IP not like", value, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPIn(List<String> values) {
            addCriterion("Source_IP in", values, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPNotIn(List<String> values) {
            addCriterion("Source_IP not in", values, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPBetween(String value1, String value2) {
            addCriterion("Source_IP between", value1, value2, "source_IP");
            return (Criteria) this;
        }

        public Criteria andSource_IPNotBetween(String value1, String value2) {
            addCriterion("Source_IP not between", value1, value2, "source_IP");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberIsNull() {
            addCriterion("Priority_Number is null");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberIsNotNull() {
            addCriterion("Priority_Number is not null");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberEqualTo(Integer value) {
            addCriterion("Priority_Number =", value, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberNotEqualTo(Integer value) {
            addCriterion("Priority_Number <>", value, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberGreaterThan(Integer value) {
            addCriterion("Priority_Number >", value, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("Priority_Number >=", value, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberLessThan(Integer value) {
            addCriterion("Priority_Number <", value, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberLessThanOrEqualTo(Integer value) {
            addCriterion("Priority_Number <=", value, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberIn(List<Integer> values) {
            addCriterion("Priority_Number in", values, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberNotIn(List<Integer> values) {
            addCriterion("Priority_Number not in", values, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberBetween(Integer value1, Integer value2) {
            addCriterion("Priority_Number between", value1, value2, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andPriority_NumberNotBetween(Integer value1, Integer value2) {
            addCriterion("Priority_Number not between", value1, value2, "priority_Number");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeIsNull() {
            addCriterion("Audit_Status_Code is null");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeIsNotNull() {
            addCriterion("Audit_Status_Code is not null");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeEqualTo(String value) {
            addCriterion("Audit_Status_Code =", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeNotEqualTo(String value) {
            addCriterion("Audit_Status_Code <>", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeGreaterThan(String value) {
            addCriterion("Audit_Status_Code >", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeGreaterThanOrEqualTo(String value) {
            addCriterion("Audit_Status_Code >=", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeLessThan(String value) {
            addCriterion("Audit_Status_Code <", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeLessThanOrEqualTo(String value) {
            addCriterion("Audit_Status_Code <=", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeLike(String value) {
            addCriterion("Audit_Status_Code like", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeNotLike(String value) {
            addCriterion("Audit_Status_Code not like", value, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeIn(List<String> values) {
            addCriterion("Audit_Status_Code in", values, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeNotIn(List<String> values) {
            addCriterion("Audit_Status_Code not in", values, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeBetween(String value1, String value2) {
            addCriterion("Audit_Status_Code between", value1, value2, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Status_CodeNotBetween(String value1, String value2) {
            addCriterion("Audit_Status_Code not between", value1, value2, "audit_Status_Code");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdIsNull() {
            addCriterion("Audit_Admin_User_Id is null");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdIsNotNull() {
            addCriterion("Audit_Admin_User_Id is not null");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdEqualTo(Integer value) {
            addCriterion("Audit_Admin_User_Id =", value, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdNotEqualTo(Integer value) {
            addCriterion("Audit_Admin_User_Id <>", value, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdGreaterThan(Integer value) {
            addCriterion("Audit_Admin_User_Id >", value, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Audit_Admin_User_Id >=", value, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdLessThan(Integer value) {
            addCriterion("Audit_Admin_User_Id <", value, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdLessThanOrEqualTo(Integer value) {
            addCriterion("Audit_Admin_User_Id <=", value, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdIn(List<Integer> values) {
            addCriterion("Audit_Admin_User_Id in", values, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdNotIn(List<Integer> values) {
            addCriterion("Audit_Admin_User_Id not in", values, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdBetween(Integer value1, Integer value2) {
            addCriterion("Audit_Admin_User_Id between", value1, value2, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_Admin_User_IdNotBetween(Integer value1, Integer value2) {
            addCriterion("Audit_Admin_User_Id not between", value1, value2, "audit_Admin_User_Id");
            return (Criteria) this;
        }

        public Criteria andAudit_DateIsNull() {
            addCriterion("Audit_Date is null");
            return (Criteria) this;
        }

        public Criteria andAudit_DateIsNotNull() {
            addCriterion("Audit_Date is not null");
            return (Criteria) this;
        }

        public Criteria andAudit_DateEqualTo(Date value) {
            addCriterion("Audit_Date =", value, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateNotEqualTo(Date value) {
            addCriterion("Audit_Date <>", value, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateGreaterThan(Date value) {
            addCriterion("Audit_Date >", value, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateGreaterThanOrEqualTo(Date value) {
            addCriterion("Audit_Date >=", value, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateLessThan(Date value) {
            addCriterion("Audit_Date <", value, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateLessThanOrEqualTo(Date value) {
            addCriterion("Audit_Date <=", value, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateIn(List<Date> values) {
            addCriterion("Audit_Date in", values, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateNotIn(List<Date> values) {
            addCriterion("Audit_Date not in", values, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateBetween(Date value1, Date value2) {
            addCriterion("Audit_Date between", value1, value2, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andAudit_DateNotBetween(Date value1, Date value2) {
            addCriterion("Audit_Date not between", value1, value2, "audit_Date");
            return (Criteria) this;
        }

        public Criteria andSub_CodeIsNull() {
            addCriterion("Sub_Code is null");
            return (Criteria) this;
        }

        public Criteria andSub_CodeIsNotNull() {
            addCriterion("Sub_Code is not null");
            return (Criteria) this;
        }

        public Criteria andSub_CodeEqualTo(String value) {
            addCriterion("Sub_Code =", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeNotEqualTo(String value) {
            addCriterion("Sub_Code <>", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeGreaterThan(String value) {
            addCriterion("Sub_Code >", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeGreaterThanOrEqualTo(String value) {
            addCriterion("Sub_Code >=", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeLessThan(String value) {
            addCriterion("Sub_Code <", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeLessThanOrEqualTo(String value) {
            addCriterion("Sub_Code <=", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeLike(String value) {
            addCriterion("Sub_Code like", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeNotLike(String value) {
            addCriterion("Sub_Code not like", value, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeIn(List<String> values) {
            addCriterion("Sub_Code in", values, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeNotIn(List<String> values) {
            addCriterion("Sub_Code not in", values, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeBetween(String value1, String value2) {
            addCriterion("Sub_Code between", value1, value2, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSub_CodeNotBetween(String value1, String value2) {
            addCriterion("Sub_Code not between", value1, value2, "sub_Code");
            return (Criteria) this;
        }

        public Criteria andSend_TimeIsNull() {
            addCriterion("Send_Time is null");
            return (Criteria) this;
        }

        public Criteria andSend_TimeIsNotNull() {
            addCriterion("Send_Time is not null");
            return (Criteria) this;
        }

        public Criteria andSend_TimeEqualTo(Date value) {
            addCriterion("Send_Time =", value, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeNotEqualTo(Date value) {
            addCriterion("Send_Time <>", value, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeGreaterThan(Date value) {
            addCriterion("Send_Time >", value, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeGreaterThanOrEqualTo(Date value) {
            addCriterion("Send_Time >=", value, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeLessThan(Date value) {
            addCriterion("Send_Time <", value, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeLessThanOrEqualTo(Date value) {
            addCriterion("Send_Time <=", value, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeIn(List<Date> values) {
            addCriterion("Send_Time in", values, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeNotIn(List<Date> values) {
            addCriterion("Send_Time not in", values, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeBetween(Date value1, Date value2) {
            addCriterion("Send_Time between", value1, value2, "send_Time");
            return (Criteria) this;
        }

        public Criteria andSend_TimeNotBetween(Date value1, Date value2) {
            addCriterion("Send_Time not between", value1, value2, "send_Time");
            return (Criteria) this;
        }

        public Criteria andGate_IpIsNull() {
            addCriterion("Gate_Ip is null");
            return (Criteria) this;
        }

        public Criteria andGate_IpIsNotNull() {
            addCriterion("Gate_Ip is not null");
            return (Criteria) this;
        }

        public Criteria andGate_IpEqualTo(String value) {
            addCriterion("Gate_Ip =", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpNotEqualTo(String value) {
            addCriterion("Gate_Ip <>", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpGreaterThan(String value) {
            addCriterion("Gate_Ip >", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpGreaterThanOrEqualTo(String value) {
            addCriterion("Gate_Ip >=", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpLessThan(String value) {
            addCriterion("Gate_Ip <", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpLessThanOrEqualTo(String value) {
            addCriterion("Gate_Ip <=", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpLike(String value) {
            addCriterion("Gate_Ip like", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpNotLike(String value) {
            addCriterion("Gate_Ip not like", value, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpIn(List<String> values) {
            addCriterion("Gate_Ip in", values, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpNotIn(List<String> values) {
            addCriterion("Gate_Ip not in", values, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpBetween(String value1, String value2) {
            addCriterion("Gate_Ip between", value1, value2, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andGate_IpNotBetween(String value1, String value2) {
            addCriterion("Gate_Ip not between", value1, value2, "gate_Ip");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyIsNull() {
            addCriterion("Assist_Audit_Key is null");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyIsNotNull() {
            addCriterion("Assist_Audit_Key is not null");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyEqualTo(String value) {
            addCriterion("Assist_Audit_Key =", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyNotEqualTo(String value) {
            addCriterion("Assist_Audit_Key <>", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyGreaterThan(String value) {
            addCriterion("Assist_Audit_Key >", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyGreaterThanOrEqualTo(String value) {
            addCriterion("Assist_Audit_Key >=", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyLessThan(String value) {
            addCriterion("Assist_Audit_Key <", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyLessThanOrEqualTo(String value) {
            addCriterion("Assist_Audit_Key <=", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyLike(String value) {
            addCriterion("Assist_Audit_Key like", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyNotLike(String value) {
            addCriterion("Assist_Audit_Key not like", value, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyIn(List<String> values) {
            addCriterion("Assist_Audit_Key in", values, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyNotIn(List<String> values) {
            addCriterion("Assist_Audit_Key not in", values, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyBetween(String value1, String value2) {
            addCriterion("Assist_Audit_Key between", value1, value2, "assist_Audit_Key");
            return (Criteria) this;
        }

        public Criteria andAssist_Audit_KeyNotBetween(String value1, String value2) {
            addCriterion("Assist_Audit_Key not between", value1, value2, "assist_Audit_Key");
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

        public Criteria andInput_DateIsNull() {
            addCriterion("Input_Date is null");
            return (Criteria) this;
        }

        public Criteria andInput_DateIsNotNull() {
            addCriterion("Input_Date is not null");
            return (Criteria) this;
        }

        public Criteria andInput_DateEqualTo(Date value) {
            addCriterion("Input_Date =", value, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateNotEqualTo(Date value) {
            addCriterion("Input_Date <>", value, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateGreaterThan(Date value) {
            addCriterion("Input_Date >", value, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateGreaterThanOrEqualTo(Date value) {
            addCriterion("Input_Date >=", value, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateLessThan(Date value) {
            addCriterion("Input_Date <", value, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateLessThanOrEqualTo(Date value) {
            addCriterion("Input_Date <=", value, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateIn(List<Date> values) {
            addCriterion("Input_Date in", values, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateNotIn(List<Date> values) {
            addCriterion("Input_Date not in", values, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateBetween(Date value1, Date value2) {
            addCriterion("Input_Date between", value1, value2, "input_Date");
            return (Criteria) this;
        }

        public Criteria andInput_DateNotBetween(Date value1, Date value2) {
            addCriterion("Input_Date not between", value1, value2, "input_Date");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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