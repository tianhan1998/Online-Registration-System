package cn.edu.aynu.onlineRegistrationSystem.entity;

import java.util.Date;

public class matchInfo {
    private Integer matchId;

    private String matchTitle;

    private Date matchStarTime;

    private Date matchEndTime;

    private String matchMode;

    private String matchPassword;

    private Integer matchStatus;

    public matchInfo() {
    }

    public matchInfo(String matchTitle, Date matchStarTime, Date matchEndTime, String matchMode, String matchPassword, Integer matchStatus) {
        this.matchTitle = matchTitle;
        this.matchStarTime = matchStarTime;
        this.matchEndTime = matchEndTime;
        this.matchMode = matchMode;
        this.matchPassword = matchPassword;
        this.matchStatus = matchStatus;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle == null ? null : matchTitle.trim();
    }

    public Date getMatchStarTime() {
        return matchStarTime;
    }

    public void setMatchStarTime(Date matchStarTime) {
        this.matchStarTime = matchStarTime;
    }

    public Date getMatchEndTime() {
        return matchEndTime;
    }

    public void setMatchEndTime(Date matchEndTime) {
        this.matchEndTime = matchEndTime;
    }

    public String getMatchMode() {
        return matchMode;
    }

    public void setMatchMode(String matchMode) {
        this.matchMode = matchMode == null ? null : matchMode.trim();
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword == null ? null : matchPassword.trim();
    }

    public Integer getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Integer matchStatus) {
        this.matchStatus = matchStatus;
    }
}