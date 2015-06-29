package com.system.model.po;
import com.readinglife.framework.po.BasePoExample;
import java.util.ArrayList;
import java.util.List;

public class SysLoktpePOExample extends BasePoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysLoktpePOExample() {
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

        public Criteria andLoktpeIsNull() {
            addCriterion("LOKTPE is null");
            return (Criteria) this;
        }

        public Criteria andLoktpeIsNotNull() {
            addCriterion("LOKTPE is not null");
            return (Criteria) this;
        }

        public Criteria andLoktpeEqualTo(String value) {
            addCriterion("LOKTPE =", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeNotEqualTo(String value) {
            addCriterion("LOKTPE <>", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeGreaterThan(String value) {
            addCriterion("LOKTPE >", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeGreaterThanOrEqualTo(String value) {
            addCriterion("LOKTPE >=", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeLessThan(String value) {
            addCriterion("LOKTPE <", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeLessThanOrEqualTo(String value) {
            addCriterion("LOKTPE <=", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeLike(String value) {
            addCriterion("LOKTPE like", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeNotLike(String value) {
            addCriterion("LOKTPE not like", value, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeIn(List<String> values) {
            addCriterion("LOKTPE in", values, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeNotIn(List<String> values) {
            addCriterion("LOKTPE not in", values, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeBetween(String value1, String value2) {
            addCriterion("LOKTPE between", value1, value2, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeNotBetween(String value1, String value2) {
            addCriterion("LOKTPE not between", value1, value2, "loktpe");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameIsNull() {
            addCriterion("LOKTPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameIsNotNull() {
            addCriterion("LOKTPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameEqualTo(String value) {
            addCriterion("LOKTPE_NAME =", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameNotEqualTo(String value) {
            addCriterion("LOKTPE_NAME <>", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameGreaterThan(String value) {
            addCriterion("LOKTPE_NAME >", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOKTPE_NAME >=", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameLessThan(String value) {
            addCriterion("LOKTPE_NAME <", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameLessThanOrEqualTo(String value) {
            addCriterion("LOKTPE_NAME <=", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameLike(String value) {
            addCriterion("LOKTPE_NAME like", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameNotLike(String value) {
            addCriterion("LOKTPE_NAME not like", value, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameIn(List<String> values) {
            addCriterion("LOKTPE_NAME in", values, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameNotIn(List<String> values) {
            addCriterion("LOKTPE_NAME not in", values, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameBetween(String value1, String value2) {
            addCriterion("LOKTPE_NAME between", value1, value2, "loktpeName");
            return (Criteria) this;
        }

        public Criteria andLoktpeNameNotBetween(String value1, String value2) {
            addCriterion("LOKTPE_NAME not between", value1, value2, "loktpeName");
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