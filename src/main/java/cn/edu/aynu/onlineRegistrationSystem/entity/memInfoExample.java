package cn.edu.aynu.onlineRegistrationSystem.entity;

import java.util.ArrayList;
import java.util.List;

public class memInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public memInfoExample() {
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

        public Criteria andMemIdIsNull() {
            addCriterion("mem_id is null");
            return (Criteria) this;
        }

        public Criteria andMemIdIsNotNull() {
            addCriterion("mem_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemIdEqualTo(Integer value) {
            addCriterion("mem_id =", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdNotEqualTo(Integer value) {
            addCriterion("mem_id <>", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdGreaterThan(Integer value) {
            addCriterion("mem_id >", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mem_id >=", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdLessThan(Integer value) {
            addCriterion("mem_id <", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdLessThanOrEqualTo(Integer value) {
            addCriterion("mem_id <=", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdIn(List<Integer> values) {
            addCriterion("mem_id in", values, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdNotIn(List<Integer> values) {
            addCriterion("mem_id not in", values, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdBetween(Integer value1, Integer value2) {
            addCriterion("mem_id between", value1, value2, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mem_id not between", value1, value2, "memId");
            return (Criteria) this;
        }

        public Criteria andMemNameIsNull() {
            addCriterion("mem_name is null");
            return (Criteria) this;
        }

        public Criteria andMemNameIsNotNull() {
            addCriterion("mem_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemNameEqualTo(String value) {
            addCriterion("mem_name =", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotEqualTo(String value) {
            addCriterion("mem_name <>", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameGreaterThan(String value) {
            addCriterion("mem_name >", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameGreaterThanOrEqualTo(String value) {
            addCriterion("mem_name >=", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameLessThan(String value) {
            addCriterion("mem_name <", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameLessThanOrEqualTo(String value) {
            addCriterion("mem_name <=", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameLike(String value) {
            addCriterion("mem_name like", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotLike(String value) {
            addCriterion("mem_name not like", value, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameIn(List<String> values) {
            addCriterion("mem_name in", values, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotIn(List<String> values) {
            addCriterion("mem_name not in", values, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameBetween(String value1, String value2) {
            addCriterion("mem_name between", value1, value2, "memName");
            return (Criteria) this;
        }

        public Criteria andMemNameNotBetween(String value1, String value2) {
            addCriterion("mem_name not between", value1, value2, "memName");
            return (Criteria) this;
        }

        public Criteria andMemEmailIsNull() {
            addCriterion("mem_email is null");
            return (Criteria) this;
        }

        public Criteria andMemEmailIsNotNull() {
            addCriterion("mem_email is not null");
            return (Criteria) this;
        }

        public Criteria andMemEmailEqualTo(String value) {
            addCriterion("mem_email =", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailNotEqualTo(String value) {
            addCriterion("mem_email <>", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailGreaterThan(String value) {
            addCriterion("mem_email >", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailGreaterThanOrEqualTo(String value) {
            addCriterion("mem_email >=", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailLessThan(String value) {
            addCriterion("mem_email <", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailLessThanOrEqualTo(String value) {
            addCriterion("mem_email <=", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailLike(String value) {
            addCriterion("mem_email like", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailNotLike(String value) {
            addCriterion("mem_email not like", value, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailIn(List<String> values) {
            addCriterion("mem_email in", values, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailNotIn(List<String> values) {
            addCriterion("mem_email not in", values, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailBetween(String value1, String value2) {
            addCriterion("mem_email between", value1, value2, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemEmailNotBetween(String value1, String value2) {
            addCriterion("mem_email not between", value1, value2, "memEmail");
            return (Criteria) this;
        }

        public Criteria andMemSexIsNull() {
            addCriterion("mem_sex is null");
            return (Criteria) this;
        }

        public Criteria andMemSexIsNotNull() {
            addCriterion("mem_sex is not null");
            return (Criteria) this;
        }

        public Criteria andMemSexEqualTo(String value) {
            addCriterion("mem_sex =", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexNotEqualTo(String value) {
            addCriterion("mem_sex <>", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexGreaterThan(String value) {
            addCriterion("mem_sex >", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexGreaterThanOrEqualTo(String value) {
            addCriterion("mem_sex >=", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexLessThan(String value) {
            addCriterion("mem_sex <", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexLessThanOrEqualTo(String value) {
            addCriterion("mem_sex <=", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexLike(String value) {
            addCriterion("mem_sex like", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexNotLike(String value) {
            addCriterion("mem_sex not like", value, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexIn(List<String> values) {
            addCriterion("mem_sex in", values, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexNotIn(List<String> values) {
            addCriterion("mem_sex not in", values, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexBetween(String value1, String value2) {
            addCriterion("mem_sex between", value1, value2, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemSexNotBetween(String value1, String value2) {
            addCriterion("mem_sex not between", value1, value2, "memSex");
            return (Criteria) this;
        }

        public Criteria andMemPasswordIsNull() {
            addCriterion("mem_password is null");
            return (Criteria) this;
        }

        public Criteria andMemPasswordIsNotNull() {
            addCriterion("mem_password is not null");
            return (Criteria) this;
        }

        public Criteria andMemPasswordEqualTo(String value) {
            addCriterion("mem_password =", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordNotEqualTo(String value) {
            addCriterion("mem_password <>", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordGreaterThan(String value) {
            addCriterion("mem_password >", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("mem_password >=", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordLessThan(String value) {
            addCriterion("mem_password <", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordLessThanOrEqualTo(String value) {
            addCriterion("mem_password <=", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordLike(String value) {
            addCriterion("mem_password like", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordNotLike(String value) {
            addCriterion("mem_password not like", value, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordIn(List<String> values) {
            addCriterion("mem_password in", values, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordNotIn(List<String> values) {
            addCriterion("mem_password not in", values, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordBetween(String value1, String value2) {
            addCriterion("mem_password between", value1, value2, "memPassword");
            return (Criteria) this;
        }

        public Criteria andMemPasswordNotBetween(String value1, String value2) {
            addCriterion("mem_password not between", value1, value2, "memPassword");
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