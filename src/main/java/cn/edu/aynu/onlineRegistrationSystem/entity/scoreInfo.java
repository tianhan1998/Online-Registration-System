package cn.edu.aynu.onlineRegistrationSystem.entity;

public class scoreInfo {
    private Integer id;

    private Integer matchId;

    private Integer memId;

    private Double memScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public Double getMemScore() {
        return memScore;
    }

    public void setMemScore(Double memScore) {
        this.memScore = memScore;
    }
}