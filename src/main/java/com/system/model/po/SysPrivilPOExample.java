package com.system.model.po;

import com.readinglife.framework.po.BasePoExample;
import java.util.ArrayList;
import java.util.List;

public class SysPrivilPOExample extends BasePoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysPrivilPOExample() {
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

        public Criteria andPrivilIdIsNull() {
            addCriterion("PRIVIL_ID is null");
            return (Criteria) this;
        }

        public Criteria andPrivilIdIsNotNull() {
            addCriterion("PRIVIL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilIdEqualTo(String value) {
            addCriterion("PRIVIL_ID =", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdNotEqualTo(String value) {
            addCriterion("PRIVIL_ID <>", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdGreaterThan(String value) {
            addCriterion("PRIVIL_ID >", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRIVIL_ID >=", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdLessThan(String value) {
            addCriterion("PRIVIL_ID <", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdLessThanOrEqualTo(String value) {
            addCriterion("PRIVIL_ID <=", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdLike(String value) {
            addCriterion("PRIVIL_ID like", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdNotLike(String value) {
            addCriterion("PRIVIL_ID not like", value, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdIn(List<String> values) {
            addCriterion("PRIVIL_ID in", values, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdNotIn(List<String> values) {
            addCriterion("PRIVIL_ID not in", values, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdBetween(String value1, String value2) {
            addCriterion("PRIVIL_ID between", value1, value2, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilIdNotBetween(String value1, String value2) {
            addCriterion("PRIVIL_ID not between", value1, value2, "privilId");
            return (Criteria) this;
        }

        public Criteria andPrivilPidIsNull() {
            addCriterion("PRIVIL_PID is null");
            return (Criteria) this;
        }

        public Criteria andPrivilPidIsNotNull() {
            addCriterion("PRIVIL_PID is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilPidEqualTo(String value) {
            addCriterion("PRIVIL_PID =", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidNotEqualTo(String value) {
            addCriterion("PRIVIL_PID <>", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidGreaterThan(String value) {
            addCriterion("PRIVIL_PID >", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidGreaterThanOrEqualTo(String value) {
            addCriterion("PRIVIL_PID >=", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidLessThan(String value) {
            addCriterion("PRIVIL_PID <", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidLessThanOrEqualTo(String value) {
            addCriterion("PRIVIL_PID <=", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidLike(String value) {
            addCriterion("PRIVIL_PID like", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidNotLike(String value) {
            addCriterion("PRIVIL_PID not like", value, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidIn(List<String> values) {
            addCriterion("PRIVIL_PID in", values, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidNotIn(List<String> values) {
            addCriterion("PRIVIL_PID not in", values, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidBetween(String value1, String value2) {
            addCriterion("PRIVIL_PID between", value1, value2, "privilPid");
            return (Criteria) this;
        }

        public Criteria andPrivilPidNotBetween(String value1, String value2) {
            addCriterion("PRIVIL_PID not between", value1, value2, "privilPid");
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

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andPrograCodeIsNull() {
            addCriterion("PROGRA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPrograCodeIsNotNull() {
            addCriterion("PROGRA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPrograCodeEqualTo(String value) {
            addCriterion("PROGRA_CODE =", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeNotEqualTo(String value) {
            addCriterion("PROGRA_CODE <>", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeGreaterThan(String value) {
            addCriterion("PROGRA_CODE >", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PROGRA_CODE >=", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeLessThan(String value) {
            addCriterion("PROGRA_CODE <", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeLessThanOrEqualTo(String value) {
            addCriterion("PROGRA_CODE <=", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeLike(String value) {
            addCriterion("PROGRA_CODE like", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeNotLike(String value) {
            addCriterion("PROGRA_CODE not like", value, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeIn(List<String> values) {
            addCriterion("PROGRA_CODE in", values, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeNotIn(List<String> values) {
            addCriterion("PROGRA_CODE not in", values, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeBetween(String value1, String value2) {
            addCriterion("PROGRA_CODE between", value1, value2, "prograCode");
            return (Criteria) this;
        }

        public Criteria andPrograCodeNotBetween(String value1, String value2) {
            addCriterion("PROGRA_CODE not between", value1, value2, "prograCode");
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