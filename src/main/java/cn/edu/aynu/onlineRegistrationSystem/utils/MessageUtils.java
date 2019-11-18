package cn.edu.aynu.onlineRegistrationSystem.utils;

import cn.edu.aynu.onlineRegistrationSystem.entity.MessageInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import cn.edu.aynu.onlineRegistrationSystem.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageUtils {
    @Autowired
    private MessageService messageService;

    private static MessageUtils messageUtils;
    @PostConstruct
    public void init(){
        messageUtils=this;
    }

    /**
     * 手动发送信息
     * @param message
     * @return
     */
    public static boolean sendMessage(MessageInfo message){
        if(messageUtils.messageService.insertMessage(message)>0) {
            if (messageUtils.messageService.insertSendRecord(message.getMessageId(), message.getMessageFromId()) > 0) {
                if (messageUtils.messageService.insertReceiveRecord(message.getMessageId(), message.getMessageToId()) > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
