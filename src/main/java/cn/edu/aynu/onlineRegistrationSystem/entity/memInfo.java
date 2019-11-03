package cn.edu.aynu.onlineRegistrationSystem.entity;

public class memInfo {
    private Integer memId;

    private String memName;

    private String memEmail;

    private String memSex;

    private String memPassword;

    public memInfo() {
    }

    public memInfo(Integer memId, String memName, String memEmail, String memSex, String memPassword) {
        this.memId = memId;
        this.memName = memName;
        this.memEmail = memEmail;
        this.memSex = memSex;
        this.memPassword = memPassword;
    }

    public Integer getMemId() {
        return memId;
    }

    public void setMemId(Integer memId) {
        this.memId = memId;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName == null ? null : memName.trim();
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail == null ? null : memEmail.trim();
    }

    public String getMemSex() {
        return memSex;
    }

    public void setMemSex(String memSex) {
        this.memSex = memSex == null ? null : memSex.trim();
    }

    public String getMemPassword() {
        return memPassword;
    }

    public void setMemPassword(String memPassword) {
        this.memPassword = memPassword == null ? null : memPassword.trim();
    }
}