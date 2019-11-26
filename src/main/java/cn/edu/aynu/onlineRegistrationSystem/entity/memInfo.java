package cn.edu.aynu.onlineRegistrationSystem.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class memInfo {
    @NotNull
    @Min(100000000)
    @Max(999999999)
    private Integer memId;
    @NotBlank
    private String memName;
    @NotBlank
    @Email
    private String memEmail;
    @NotBlank
    private String memSex;
    @NotBlank
    private String memPassword;

    @Override
    public String toString() {
        return "memInfo{" +
                "memId=" + memId +
                ", memName='" + memName + '\'' +
                ", memEmail='" + memEmail + '\'' +
                ", memSex='" + memSex + '\'' +
                ", memPassword='" + memPassword + '\'' +
                '}';
    }

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