package cn.edu.aynu.onlineRegistrationSystem.entity;

import java.util.ArrayList;
import java.util.List;

public class teamInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public teamInfoExample() {
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

        public Criteria andTeamIdIsNull() {
            addCriterion("team_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("team_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(Integer value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(Integer value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(Integer value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(Integer value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(Integer value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<Integer> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<Integer> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(Integer value1, Integer value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNull() {
            addCriterion("team_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNotNull() {
            addCriterion("team_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamNameEqualTo(String value) {
            addCriterion("team_name =", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotEqualTo(String value) {
            addCriterion("team_name <>", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThan(String value) {
            addCriterion("team_name >", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_name >=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThan(String value) {
            addCriterion("team_name <", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThanOrEqualTo(String value) {
            addCriterion("team_name <=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLike(String value) {
            addCriterion("team_name like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotLike(String value) {
            addCriterion("team_name not like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameIn(List<String> values) {
            addCriterion("team_name in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotIn(List<String> values) {
            addCriterion("team_name not in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameBetween(String value1, String value2) {
            addCriterion("team_name between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotBetween(String value1, String value2) {
            addCriterion("team_name not between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamAccountIsNull() {
            addCriterion("team_account is null");
            return (Criteria) this;
        }

        public Criteria andTeamAccountIsNotNull() {
            addCriterion("team_account is not null");
            return (Criteria) this;
        }

        public Criteria andTeamAccountEqualTo(String value) {
            addCriterion("team_account =", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountNotEqualTo(String value) {
            addCriterion("team_account <>", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountGreaterThan(String value) {
            addCriterion("team_account >", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountGreaterThanOrEqualTo(String value) {
            addCriterion("team_account >=", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountLessThan(String value) {
            addCriterion("team_account <", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountLessThanOrEqualTo(String value) {
            addCriterion("team_account <=", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountLike(String value) {
            addCriterion("team_account like", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountNotLike(String value) {
            addCriterion("team_account not like", value, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountIn(List<String> values) {
            addCriterion("team_account in", values, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountNotIn(List<String> values) {
            addCriterion("team_account not in", values, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountBetween(String value1, String value2) {
            addCriterion("team_account between", value1, value2, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamAccountNotBetween(String value1, String value2) {
            addCriterion("team_account not between", value1, value2, "teamAccount");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordIsNull() {
            addCriterion("team_password is null");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordIsNotNull() {
            addCriterion("team_password is not null");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordEqualTo(String value) {
            addCriterion("team_password =", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordNotEqualTo(String value) {
            addCriterion("team_password <>", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordGreaterThan(String value) {
            addCriterion("team_password >", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("team_password >=", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordLessThan(String value) {
            addCriterion("team_password <", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordLessThanOrEqualTo(String value) {
            addCriterion("team_password <=", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordLike(String value) {
            addCriterion("team_password like", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordNotLike(String value) {
            addCriterion("team_password not like", value, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordIn(List<String> values) {
            addCriterion("team_password in", values, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordNotIn(List<String> values) {
            addCriterion("team_password not in", values, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordBetween(String value1, String value2) {
            addCriterion("team_password between", value1, value2, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andTeamPasswordNotBetween(String value1, String value2) {
            addCriterion("team_password not between", value1, value2, "teamPassword");
            return (Criteria) this;
        }

        public Criteria andMemId1IsNull() {
            addCriterion("mem_id1 is null");
            return (Criteria) this;
        }

        public Criteria andMemId1IsNotNull() {
            addCriterion("mem_id1 is not null");
            return (Criteria) this;
        }

        public Criteria andMemId1EqualTo(Integer value) {
            addCriterion("mem_id1 =", value, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1NotEqualTo(Integer value) {
            addCriterion("mem_id1 <>", value, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1GreaterThan(Integer value) {
            addCriterion("mem_id1 >", value, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1GreaterThanOrEqualTo(Integer value) {
            addCriterion("mem_id1 >=", value, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1LessThan(Integer value) {
            addCriterion("mem_id1 <", value, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1LessThanOrEqualTo(Integer value) {
            addCriterion("mem_id1 <=", value, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1In(List<Integer> values) {
            addCriterion("mem_id1 in", values, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1NotIn(List<Integer> values) {
            addCriterion("mem_id1 not in", values, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1Between(Integer value1, Integer value2) {
            addCriterion("mem_id1 between", value1, value2, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId1NotBetween(Integer value1, Integer value2) {
            addCriterion("mem_id1 not between", value1, value2, "memId1");
            return (Criteria) this;
        }

        public Criteria andMemId2IsNull() {
            addCriterion("mem_id2 is null");
            return (Criteria) this;
        }

        public Criteria andMemId2IsNotNull() {
            addCriterion("mem_id2 is not null");
            return (Criteria) this;
        }

        public Criteria andMemId2EqualTo(Integer value) {
            addCriterion("mem_id2 =", value, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2NotEqualTo(Integer value) {
            addCriterion("mem_id2 <>", value, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2GreaterThan(Integer value) {
            addCriterion("mem_id2 >", value, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2GreaterThanOrEqualTo(Integer value) {
            addCriterion("mem_id2 >=", value, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2LessThan(Integer value) {
            addCriterion("mem_id2 <", value, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2LessThanOrEqualTo(Integer value) {
            addCriterion("mem_id2 <=", value, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2In(List<Integer> values) {
            addCriterion("mem_id2 in", values, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2NotIn(List<Integer> values) {
            addCriterion("mem_id2 not in", values, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2Between(Integer value1, Integer value2) {
            addCriterion("mem_id2 between", value1, value2, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId2NotBetween(Integer value1, Integer value2) {
            addCriterion("mem_id2 not between", value1, value2, "memId2");
            return (Criteria) this;
        }

        public Criteria andMemId3IsNull() {
            addCriterion("mem_id3 is null");
            return (Criteria) this;
        }

        public Criteria andMemId3IsNotNull() {
            addCriterion("mem_id3 is not null");
            return (Criteria) this;
        }

        public Criteria andMemId3EqualTo(Integer value) {
            addCriterion("mem_id3 =", value, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3NotEqualTo(Integer value) {
            addCriterion("mem_id3 <>", value, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3GreaterThan(Integer value) {
            addCriterion("mem_id3 >", value, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3GreaterThanOrEqualTo(Integer value) {
            addCriterion("mem_id3 >=", value, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3LessThan(Integer value) {
            addCriterion("mem_id3 <", value, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3LessThanOrEqualTo(Integer value) {
            addCriterion("mem_id3 <=", value, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3In(List<Integer> values) {
            addCriterion("mem_id3 in", values, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3NotIn(List<Integer> values) {
            addCriterion("mem_id3 not in", values, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3Between(Integer value1, Integer value2) {
            addCriterion("mem_id3 between", value1, value2, "memId3");
            return (Criteria) this;
        }

        public Criteria andMemId3NotBetween(Integer value1, Integer value2) {
            addCriterion("mem_id3 not between", value1, value2, "memId3");
            return (Criteria) this;
        }

        public Criteria andTeamEmailIsNull() {
            addCriterion("team_email is null");
            return (Criteria) this;
        }

        public Criteria andTeamEmailIsNotNull() {
            addCriterion("team_email is not null");
            return (Criteria) this;
        }

        public Criteria andTeamEmailEqualTo(String value) {
            addCriterion("team_email =", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailNotEqualTo(String value) {
            addCriterion("team_email <>", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailGreaterThan(String value) {
            addCriterion("team_email >", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailGreaterThanOrEqualTo(String value) {
            addCriterion("team_email >=", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailLessThan(String value) {
            addCriterion("team_email <", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailLessThanOrEqualTo(String value) {
            addCriterion("team_email <=", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailLike(String value) {
            addCriterion("team_email like", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailNotLike(String value) {
            addCriterion("team_email not like", value, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailIn(List<String> values) {
            addCriterion("team_email in", values, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailNotIn(List<String> values) {
            addCriterion("team_email not in", values, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailBetween(String value1, String value2) {
            addCriterion("team_email between", value1, value2, "teamEmail");
            return (Criteria) this;
        }

        public Criteria andTeamEmailNotBetween(String value1, String value2) {
            addCriterion("team_email not between", value1, value2, "teamEmail");
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