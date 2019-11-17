package cn.edu.aynu.onlineRegistrationSystem.entity;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/17 0017
 */
public class InviteInfo {
    private Integer inviteId;
    private Integer inviteFromId;
    private Integer inviteToId;

    @Override
    public String toString() {
        return "InviteInfo{" +
                "inviteId=" + inviteId +
                ", inviteFromId=" + inviteFromId +
                ", inviteToId=" + inviteToId +
                '}';
    }

    public InviteInfo() {
    }

    public InviteInfo(Integer inviteFromId, Integer inviteToId) {
        this.inviteFromId = inviteFromId;
        this.inviteToId = inviteToId;
    }

    public Integer getInviteId() {
        return inviteId;
    }

    public void setInviteId(Integer inviteId) {
        this.inviteId = inviteId;
    }

    public Integer getInviteFromId() {
        return inviteFromId;
    }

    public void setInviteFromId(Integer inviteFromId) {
        this.inviteFromId = inviteFromId;
    }

    public Integer getInviteToId() {
        return inviteToId;
    }

    public void setInviteToId(Integer inviteToId) {
        this.inviteToId = inviteToId;
    }
}
