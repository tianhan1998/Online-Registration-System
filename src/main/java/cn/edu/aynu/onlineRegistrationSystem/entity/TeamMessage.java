package cn.edu.aynu.onlineRegistrationSystem.entity;

import java.util.List;

public class TeamMessage {
    private Integer teamId;

    private String teamName;

    private Integer memId1;
    private String memName1;
    private String memSex1;

    private Integer memId2;
    private String memName2;
    private String memSex2;

    private Integer memId3;
    private String memName3;
    private String memSex3;

    private String teamEmail;
    public TeamMessage(){};
    public TeamMessage(teamInfo team){
        this.teamName=team.getTeamName();
        this.teamId=team.getTeamId();
        this.teamEmail=team.getTeamEmail();
    }

    public TeamMessage(teamInfo team, List<memInfo>users){
        this.teamId=team.getTeamId();
        this.teamName=team.getTeamName();
        if(users.get(0)!=null) {
            this.memId1 = users.get(0).getMemId();
            this.memName1 = users.get(0).getMemName();
            this.memSex1=users.get(0).getMemSex();
        }else if(users.get(1)!=null){
            this.memId2 = users.get(1).getMemId();
            this.memName2 = users.get(1).getMemName();
            this.memSex2=users.get(1).getMemSex();
        }else if(users.get(2)!=null){
            this.memId3 = users.get(2).getMemId();
            this.memName3 = users.get(2).getMemName();
            this.memSex3=users.get(2).getMemSex();
        }
    }

    @Override
    public String toString() {
        return "TeamMessage{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", memId1=" + memId1 +
                ", memName1='" + memName1 + '\'' +
                ", memSex1='" + memSex1 + '\'' +
                ", memId2=" + memId2 +
                ", memName2='" + memName2 + '\'' +
                ", memSex2='" + memSex2 + '\'' +
                ", memId3=" + memId3 +
                ", memName3='" + memName3 + '\'' +
                ", memSex3='" + memSex3 + '\'' +
                ", teamEmail='" + teamEmail + '\'' +
                '}';
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
        this.teamName = teamName;
    }

    public Integer getMemId1() {
        return memId1;
    }

    public void setMemId1(Integer memId1) {
        this.memId1 = memId1;
    }

    public String getMemName1() {
        return memName1;
    }

    public void setMemName1(String memName1) {
        this.memName1 = memName1;
    }

    public String getMemSex1() {
        return memSex1;
    }

    public void setMemSex1(String memSex1) {
        this.memSex1 = memSex1;
    }

    public Integer getMemId2() {
        return memId2;
    }

    public void setMemId2(Integer memId2) {
        this.memId2 = memId2;
    }

    public String getMemName2() {
        return memName2;
    }

    public void setMemName2(String memName2) {
        this.memName2 = memName2;
    }

    public String getMemSex2() {
        return memSex2;
    }

    public void setMemSex2(String memSex2) {
        this.memSex2 = memSex2;
    }

    public Integer getMemId3() {
        return memId3;
    }

    public void setMemId3(Integer memId3) {
        this.memId3 = memId3;
    }

    public String getMemName3() {
        return memName3;
    }

    public void setMemName3(String memName3) {
        this.memName3 = memName3;
    }

    public String getMemSex3() {
        return memSex3;
    }

    public void setMemSex3(String memSex3) {
        this.memSex3 = memSex3;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail;
    }
}
