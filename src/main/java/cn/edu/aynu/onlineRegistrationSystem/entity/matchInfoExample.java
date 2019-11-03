package cn.edu.aynu.onlineRegistrationSystem.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class matchInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public matchInfoExample() {
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

        public Criteria andMatchIdIsNull() {
            addCriterion("match_id is null");
            return (Criteria) this;
        }

        public Criteria andMatchIdIsNotNull() {
            addCriterion("match_id is not null");
            return (Criteria) this;
        }

        public Criteria andMatchIdEqualTo(Integer value) {
            addCriterion("match_id =", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotEqualTo(Integer value) {
            addCriterion("match_id <>", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdGreaterThan(Integer value) {
            addCriterion("match_id >", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_id >=", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLessThan(Integer value) {
            addCriterion("match_id <", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("match_id <=", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdIn(List<Integer> values) {
            addCriterion("match_id in", values, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotIn(List<Integer> values) {
            addCriterion("match_id not in", values, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdBetween(Integer value1, Integer value2) {
            addCriterion("match_id between", value1, value2, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("match_id not between", value1, value2, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchTitleIsNull() {
            addCriterion("match_title is null");
            return (Criteria) this;
        }

        public Criteria andMatchTitleIsNotNull() {
            addCriterion("match_title is not null");
            return (Criteria) this;
        }

        public Criteria andMatchTitleEqualTo(String value) {
            addCriterion("match_title =", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleNotEqualTo(String value) {
            addCriterion("match_title <>", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleGreaterThan(String value) {
            addCriterion("match_title >", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleGreaterThanOrEqualTo(String value) {
            addCriterion("match_title >=", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleLessThan(String value) {
            addCriterion("match_title <", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleLessThanOrEqualTo(String value) {
            addCriterion("match_title <=", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleLike(String value) {
            addCriterion("match_title like", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleNotLike(String value) {
            addCriterion("match_title not like", value, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleIn(List<String> values) {
            addCriterion("match_title in", values, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleNotIn(List<String> values) {
            addCriterion("match_title not in", values, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleBetween(String value1, String value2) {
            addCriterion("match_title between", value1, value2, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchTitleNotBetween(String value1, String value2) {
            addCriterion("match_title not between", value1, value2, "matchTitle");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeIsNull() {
            addCriterion("match_star_time is null");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeIsNotNull() {
            addCriterion("match_star_time is not null");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeEqualTo(Date value) {
            addCriterion("match_star_time =", value, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeNotEqualTo(Date value) {
            addCriterion("match_star_time <>", value, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeGreaterThan(Date value) {
            addCriterion("match_star_time >", value, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("match_star_time >=", value, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeLessThan(Date value) {
            addCriterion("match_star_time <", value, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeLessThanOrEqualTo(Date value) {
            addCriterion("match_star_time <=", value, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeIn(List<Date> values) {
            addCriterion("match_star_time in", values, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeNotIn(List<Date> values) {
            addCriterion("match_star_time not in", values, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeBetween(Date value1, Date value2) {
            addCriterion("match_star_time between", value1, value2, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchStarTimeNotBetween(Date value1, Date value2) {
            addCriterion("match_star_time not between", value1, value2, "matchStarTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeIsNull() {
            addCriterion("match_end_time is null");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeIsNotNull() {
            addCriterion("match_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeEqualTo(Date value) {
            addCriterion("match_end_time =", value, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeNotEqualTo(Date value) {
            addCriterion("match_end_time <>", value, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeGreaterThan(Date value) {
            addCriterion("match_end_time >", value, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("match_end_time >=", value, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeLessThan(Date value) {
            addCriterion("match_end_time <", value, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("match_end_time <=", value, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeIn(List<Date> values) {
            addCriterion("match_end_time in", values, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeNotIn(List<Date> values) {
            addCriterion("match_end_time not in", values, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeBetween(Date value1, Date value2) {
            addCriterion("match_end_time between", value1, value2, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("match_end_time not between", value1, value2, "matchEndTime");
            return (Criteria) this;
        }

        public Criteria andMatchModeIsNull() {
            addCriterion("match_mode is null");
            return (Criteria) this;
        }

        public Criteria andMatchModeIsNotNull() {
            addCriterion("match_mode is not null");
            return (Criteria) this;
        }

        public Criteria andMatchModeEqualTo(String value) {
            addCriterion("match_mode =", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeNotEqualTo(String value) {
            addCriterion("match_mode <>", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeGreaterThan(String value) {
            addCriterion("match_mode >", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeGreaterThanOrEqualTo(String value) {
            addCriterion("match_mode >=", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeLessThan(String value) {
            addCriterion("match_mode <", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeLessThanOrEqualTo(String value) {
            addCriterion("match_mode <=", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeLike(String value) {
            addCriterion("match_mode like", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeNotLike(String value) {
            addCriterion("match_mode not like", value, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeIn(List<String> values) {
            addCriterion("match_mode in", values, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeNotIn(List<String> values) {
            addCriterion("match_mode not in", values, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeBetween(String value1, String value2) {
            addCriterion("match_mode between", value1, value2, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchModeNotBetween(String value1, String value2) {
            addCriterion("match_mode not between", value1, value2, "matchMode");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordIsNull() {
            addCriterion("match_password is null");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordIsNotNull() {
            addCriterion("match_password is not null");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordEqualTo(String value) {
            addCriterion("match_password =", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordNotEqualTo(String value) {
            addCriterion("match_password <>", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordGreaterThan(String value) {
            addCriterion("match_password >", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("match_password >=", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordLessThan(String value) {
            addCriterion("match_password <", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordLessThanOrEqualTo(String value) {
            addCriterion("match_password <=", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordLike(String value) {
            addCriterion("match_password like", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordNotLike(String value) {
            addCriterion("match_password not like", value, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordIn(List<String> values) {
            addCriterion("match_password in", values, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordNotIn(List<String> values) {
            addCriterion("match_password not in", values, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordBetween(String value1, String value2) {
            addCriterion("match_password between", value1, value2, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchPasswordNotBetween(String value1, String value2) {
            addCriterion("match_password not between", value1, value2, "matchPassword");
            return (Criteria) this;
        }

        public Criteria andMatchStatusIsNull() {
            addCriterion("match_status is null");
            return (Criteria) this;
        }

        public Criteria andMatchStatusIsNotNull() {
            addCriterion("match_status is not null");
            return (Criteria) this;
        }

        public Criteria andMatchStatusEqualTo(Integer value) {
            addCriterion("match_status =", value, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusNotEqualTo(Integer value) {
            addCriterion("match_status <>", value, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusGreaterThan(Integer value) {
            addCriterion("match_status >", value, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_status >=", value, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusLessThan(Integer value) {
            addCriterion("match_status <", value, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusLessThanOrEqualTo(Integer value) {
            addCriterion("match_status <=", value, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusIn(List<Integer> values) {
            addCriterion("match_status in", values, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusNotIn(List<Integer> values) {
            addCriterion("match_status not in", values, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusBetween(Integer value1, Integer value2) {
            addCriterion("match_status between", value1, value2, "matchStatus");
            return (Criteria) this;
        }

        public Criteria andMatchStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("match_status not between", value1, value2, "matchStatus");
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