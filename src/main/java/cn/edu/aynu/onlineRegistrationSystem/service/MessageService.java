package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.MessageInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.mapper.MessageMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * company: cn.edu.aynu 算法艺术社
 * Author: Tianhan
 * Create Data: 2019/11/17 0017
 */
@Service
public class MessageService {
    @Autowired
    memInfoMapper memInfoMapper;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    teamInfoMapper teamInfoMapper;

    /**
     * 通过id获取mem
     * @param id
     * @return
     */
    public memInfo getMemById(Integer id){
        return memInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 发送消息
     * @param message
     * @return
     */
    public int insertMessage(MessageInfo message){
        return messageMapper.insertMessage(message);
    }

    /**
     * 通过id获取队伍
     * @param id
     * @return
     */
    public teamInfo getTeamById(Integer id){
        return teamInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过账号id获取所有收到的消息
     * @param id
     * @return
     */
    public List<MessageInfo> getReceivedMessagesById(Integer id){
        return messageMapper.getReceivedMessagesById(id);
    }

    /**
     * 通过主键获取指定消息
     * @param id
     * @return
     */
    public MessageInfo getMessageById(Integer id){
        return messageMapper.getMessageById(id);
    }

    /**
     * 插入发送记录
     * @param send_message_id
     * @param send_object_id
     * @return
     */
    public int insertSendRecord(Integer send_message_id,Integer send_object_id){
        return messageMapper.insertSendMessageRecord(send_message_id,send_object_id);
    }

    /**
     * 插入接收记录
     * @param re_mess_id
     * @param re_object_id
     * @return
     */
    public int insertReceiveRecord(Integer re_mess_id,Integer re_object_id){
        return messageMapper.insertReceivedMessageRecord(re_mess_id,re_object_id);
    }

    /**
     * 删除接收记录
     * @param re_mess_id
     * @param re_object_id
     * @return
     */
    public int deleteReceiveRecord(Integer re_mess_id,Integer re_object_id){
        return messageMapper.deleteReceivedMessageRecord(re_mess_id,re_object_id);
    }

    /**
     * 删除发信记录
     * @param send_message_id
     * @param send_object_id
     * @return
     */
    public int deleteSendRecord(Integer send_message_id,Integer send_object_id){
        return messageMapper.deleteSendMessageRecord(send_message_id, send_object_id);
    }

    /**
     * 通过账号id获取所有发信记录
     * @param id
     * @return
     */
    public List<MessageInfo> getSendMessageById(Integer id){
        return messageMapper.getSendMessageById(id);
    }

    /**
     * 检查发件箱是否存在对应记录
     * @param mess_id
     * @param obj_id
     * @return
     */
    public int checkExistInSendMessage(Integer mess_id,Integer obj_id){
        return messageMapper.checkExistInSendMessage(mess_id, obj_id);
    }

    /**
     * 检查收件箱是否存在对应记录
     * @param mess_id
     * @param obj_id
     * @return
     */
    public int checkExistInReceivedMessage(Integer mess_id,Integer obj_id){
        return messageMapper.checkExistInReceivedMessage(mess_id, obj_id);
    }
}
