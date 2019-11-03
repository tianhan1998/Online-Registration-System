package cn.edu.aynu.onlineRegistrationSystem.entity;

public class teamInfo {
    private Integer teamId;

    private String teamName;

    private String teamAccount;

    private String teamPassword;

    private Integer memId1;

    private Integer memId2;

    private Integer memId3;

    private String teamEmail;

    public teamInfo() {
    }

    public teamInfo(String teamName, String teamAccount, String teamPassword, Integer memId1, Integer memId2, Integer memId3, String teamEmail) {
        this.teamName = teamName;
        this.teamAccount = teamAccount;
        this.teamPassword = teamPassword;
        this.memId1 = memId1;
        this.memId2 = memId2;
        this.memId3 = memId3;
        this.teamEmail = teamEmail;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public String getTeamAccount() {
        return teamAccount;
    }

    public void setTeamAccount(String teamAccount) {
        this.teamAccount = teamAccount == null ? null : teamAccount.trim();
    }

    public String getTeamPassword() {
        return teamPassword;
    }

    public void setTeamPassword(String teamPassword) {
        this.teamPassword = teamPassword == null ? null : teamPassword.trim();
    }

    public Integer getMemId1() {
        return memId1;
    }

    public void setMemId1(Integer memId1) {
        this.memId1 = memId1;
    }

    public Integer getMemId2() {
        return memId2;
    }

    public void setMemId2(Integer memId2) {
        this.memId2 = memId2;
    }

    public Integer getMemId3() {
        return memId3;
    }

    public void setMemId3(Integer memId3) {
        this.memId3 = memId3;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail == null ? null : teamEmail.trim();
    }
}