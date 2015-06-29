package com.system.model.po;

import com.readinglife.framework.po.BasePoExample;
import java.util.ArrayList;
import java.util.List;

public class SysDepartPOExample extends BasePoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDepartPOExample() {
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

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDepartIdIsNull() {
            addCriterion("DEPART_ID is null");
            return (Criteria) this;
        }

        public Criteria andDepartIdIsNotNull() {
            addCriterion("DEPART_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDepartIdEqualTo(String value) {
            addCriterion("DEPART_ID =", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotEqualTo(String value) {
            addCriterion("DEPART_ID <>", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThan(String value) {
            addCriterion("DEPART_ID >", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEPART_ID >=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThan(String value) {
            addCriterion("DEPART_ID <", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLessThanOrEqualTo(String value) {
            addCriterion("DEPART_ID <=", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdLike(String value) {
            addCriterion("DEPART_ID like", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotLike(String value) {
            addCriterion("DEPART_ID not like", value, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdIn(List<String> values) {
            addCriterion("DEPART_ID in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotIn(List<String> values) {
            addCriterion("DEPART_ID not in", values, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdBetween(String value1, String value2) {
            addCriterion("DEPART_ID between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartIdNotBetween(String value1, String value2) {
            addCriterion("DEPART_ID not between", value1, value2, "departId");
            return (Criteria) this;
        }

        public Criteria andDepartPidIsNull() {
            addCriterion("DEPART_PID is null");
            return (Criteria) this;
        }

        public Criteria andDepartPidIsNotNull() {
            addCriterion("DEPART_PID is not null");
            return (Criteria) this;
        }

        public Criteria andDepartPidEqualTo(String value) {
            addCriterion("DEPART_PID =", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidNotEqualTo(String value) {
            addCriterion("DEPART_PID <>", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidGreaterThan(String value) {
            addCriterion("DEPART_PID >", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidGreaterThanOrEqualTo(String value) {
            addCriterion("DEPART_PID >=", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidLessThan(String value) {
            addCriterion("DEPART_PID <", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidLessThanOrEqualTo(String value) {
            addCriterion("DEPART_PID <=", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidLike(String value) {
            addCriterion("DEPART_PID like", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidNotLike(String value) {
            addCriterion("DEPART_PID not like", value, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidIn(List<String> values) {
            addCriterion("DEPART_PID in", values, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidNotIn(List<String> values) {
            addCriterion("DEPART_PID not in", values, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidBetween(String value1, String value2) {
            addCriterion("DEPART_PID between", value1, value2, "departPid");
            return (Criteria) this;
        }

        public Criteria andDepartPidNotBetween(String value1, String value2) {
            addCriterion("DEPART_PID not between", value1, value2, "departPid");
            return (Criteria) this;
        }

        public Criteria andCompanIdIsNull() {
            addCriterion("COMPAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanIdIsNotNull() {
            addCriterion("COMPAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanIdEqualTo(String value) {
            addCriterion("COMPAN_ID =", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdNotEqualTo(String value) {
            addCriterion("COMPAN_ID <>", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdGreaterThan(String value) {
            addCriterion("COMPAN_ID >", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMPAN_ID >=", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdLessThan(String value) {
            addCriterion("COMPAN_ID <", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdLessThanOrEqualTo(String value) {
            addCriterion("COMPAN_ID <=", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdLike(String value) {
            addCriterion("COMPAN_ID like", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdNotLike(String value) {
            addCriterion("COMPAN_ID not like", value, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdIn(List<String> values) {
            addCriterion("COMPAN_ID in", values, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdNotIn(List<String> values) {
            addCriterion("COMPAN_ID not in", values, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdBetween(String value1, String value2) {
            addCriterion("COMPAN_ID between", value1, value2, "companId");
            return (Criteria) this;
        }

        public Criteria andCompanIdNotBetween(String value1, String value2) {
            addCriterion("COMPAN_ID not between", value1, value2, "companId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andPrioriIsNull() {
            addCriterion("PRIORI is null");
            return (Criteria) this;
        }

        public Criteria andPrioriIsNotNull() {
            addCriterion("PRIORI is not null");
            return (Criteria) this;
        }

        public Criteria andPrioriEqualTo(Integer value) {
            addCriterion("PRIORI =", value, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriNotEqualTo(Integer value) {
            addCriterion("PRIORI <>", value, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriGreaterThan(Integer value) {
            addCriterion("PRIORI >", value, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRIORI >=", value, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriLessThan(Integer value) {
            addCriterion("PRIORI <", value, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriLessThanOrEqualTo(Integer value) {
            addCriterion("PRIORI <=", value, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriIn(List<Integer> values) {
            addCriterion("PRIORI in", values, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriNotIn(List<Integer> values) {
            addCriterion("PRIORI not in", values, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriBetween(Integer value1, Integer value2) {
            addCriterion("PRIORI between", value1, value2, "priori");
            return (Criteria) this;
        }

        public Criteria andPrioriNotBetween(Integer value1, Integer value2) {
            addCriterion("PRIORI not between", value1, value2, "priori");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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