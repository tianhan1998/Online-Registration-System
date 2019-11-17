package cn.edu.aynu.onlineRegistrationSystem.entity;

import java.util.Date;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/17 0017
 */
public class MessageInfo {
    private Integer messageId;
    private Integer messageFromId;
    private Integer messageToId;
    private String messageFromName;
    private String messageToName;
    private String messageText;
    private Date messageDate;

    @Override
    public String toString() {
        return "MessageInfo{" +
                "messageId=" + messageId +
                ", messageFromId=" + messageFromId +
                ", messageToId=" + messageToId +
                ", messageFromName='" + messageFromName + '\'' +
                ", messageToName='" + messageToName + '\'' +
                ", messageText='" + messageText + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageFromId() {
        return messageFromId;
    }

    public void setMessageFromId(Integer messageFromId) {
        this.messageFromId = messageFromId;
    }

    public Integer getMessageToId() {
        return messageToId;
    }

    public void setMessageToId(Integer messageToId) {
        this.messageToId = messageToId;
    }

    public String getMessageFromName() {
        return messageFromName;
    }

    public void setMessageFromName(String messageFromName) {
        this.messageFromName = messageFromName;
    }

    public String getMessageToName() {
        return messageToName;
    }

    public void setMessageToName(String messageToName) {
        this.messageToName = messageToName;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public MessageInfo() {
    }

    public MessageInfo(Integer messageFromId, Integer messageToId, String messageFromName, String messageToName, String messageText, Date messageDate) {
        this.messageFromId = messageFromId;
        this.messageToId = messageToId;
        this.messageFromName = messageFromName;
        this.messageToName = messageToName;
        this.messageText = messageText;
        this.messageDate = messageDate;
    }
}
