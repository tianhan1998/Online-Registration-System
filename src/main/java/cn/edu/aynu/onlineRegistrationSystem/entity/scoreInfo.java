package cn.edu.aynu.onlineRegistrationSystem.entity;

public class scoreInfo {
    private Integer id;

    private Integer matchId;

    private Integer memId;

    private Double memScore;

    //TODO 加了一个无参的构造方法
    public scoreInfo() {}
    public scoreInfo(Integer matchId) {
        this.matchId = matchId;
    }

    public scoreInfo(Integer matchId, Integer memId, Double memScore) {
        this.matchId = matchId;
        this.memId = memId;
        this.memScore = memScore;
    }

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