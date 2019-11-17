package cn.edu.aynu.onlineRegistrationSystem.mapper;

import cn.edu.aynu.onlineRegistrationSystem.entity.MessageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/17 0017
 */
@Repository
public interface MessageMapper {
    int insertMessage(MessageInfo message);
    List<MessageInfo> getMessagesById(Integer id);
    MessageInfo getMessageById(Integer id);
    int insertSendMessageRecord(@Param("send_message_id")Integer send_message_id,@Param("send_object_id")Integer send_object_id);
    int insertReceiveMessageRecord(@Param("receive_message_id")Integer receive_message_id,@Param("receive_object_id")Integer receive_object_id);
    int deleteReceivedMessageRecord(@Param("receive_message_id")Integer receive_message_id,@Param("receive_object_id")Integer receive_object_id);

}
