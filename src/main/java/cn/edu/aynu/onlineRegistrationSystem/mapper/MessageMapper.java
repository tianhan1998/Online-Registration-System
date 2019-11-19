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
    List<MessageInfo> getReceivedMessagesById(Integer id);
    List<MessageInfo> getSendMessageById(Integer id);
    MessageInfo getMessageById(Integer id);
    int insertSendMessageRecord(@Param("send_message_id")Integer send_message_id,@Param("send_object_id")Integer send_object_id);
    int insertReceivedMessageRecord(@Param("receive_message_id")Integer receive_message_id,@Param("receive_object_id")Integer receive_object_id);
    int deleteReceivedMessageRecord(@Param("receive_message_id")Integer receive_message_id,@Param("receive_object_id")Integer receive_object_id);
    int deleteSendMessageRecord(@Param("send_message_id")Integer send_message_id,@Param("send_object_id")Integer send_object_id);
    int checkExistInSendMessage(@Param("message_id")Integer messageId,@Param("object_id")Integer object_id);
    int checkExistInReceivedMessage(@Param("message_id")Integer messageId,@Param("object_id")Integer object_id);
    List<MessageInfo> selectListMessageByIds(@Param("ids") List<Integer> ids);

}
