package cn.edu.aynu.onlineRegistrationSystem.entity;

public class memMatch {
    private Integer id;

    private Integer memId;

    private Integer matchId;

    public memMatch() {
    }

    public memMatch(Integer memId, Integer matchId) {
        this.memId = memId;
        this.matchId = matchId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }
}