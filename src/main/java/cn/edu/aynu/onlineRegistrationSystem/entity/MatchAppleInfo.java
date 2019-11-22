package cn.edu.aynu.onlineRegistrationSystem.entity;

/**
 * 比赛报名
 */
public class MatchAppleInfo {
    private String matchTitle;//比赛标题
    private Integer matchId;//比赛id
    private memInfo memInfo;//个人参赛
    private teamInfo teamInfo;//团队参赛

    public MatchAppleInfo() {
    }

    public MatchAppleInfo(String matchTitle, Integer matchId, cn.edu.aynu.onlineRegistrationSystem.entity.memInfo memInfo, cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo teamInfo) {
        this.matchTitle = matchTitle;
        this.matchId = matchId;
        this.memInfo = memInfo;
        this.teamInfo = teamInfo;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public cn.edu.aynu.onlineRegistrationSystem.entity.memInfo getMemInfo() {
        return memInfo;
    }

    public void setMemInfo(cn.edu.aynu.onlineRegistrationSystem.entity.memInfo memInfo) {
        this.memInfo = memInfo;
    }

    public cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo teamInfo) {
        this.teamInfo = teamInfo;
    }
}
