package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.MessageInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * company: cn.edu.aynu 算法艺术社
 * Author: Tianhan
 * Create Data: 2019/11/17 0017
 */
@RestController
// @RequestMapping("/online")
public class MessageController {

    @Autowired
    MessageService service;
    /**
     * 前端传入消息传递的对象id以及消息本体和发送时间以及目标的类型
     * @param messageToId 要发送到的id（团队或个人）
     * @param type 要发送到的账号类型 0是个人，1是团队
     * @param messageText 消息本体
     * @param messageDate 消息发送时间
     * @return
     */
    @PostMapping(value = "/sendMessage",produces = "application/json;charset=utf-8")
    public JSONObject insertMessage(Integer messageToId, Integer type, String messageText, Date messageDate, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();
        MessageInfo message = null;//发送的信息
        Object target;//发送目标，队伍或个人类型
        Integer myId;
        try {
            if ("mem".equals(session.getAttribute("type"))) {//个人账号发送消息
                memInfo user = (memInfo) session.getAttribute("user");
                myId=user.getMemId();
                if (type == 0) {//抵达地址类型是个人账户
                    target = service.getMemById(messageToId);
                    memInfo targetUser = (memInfo) target;
                    if (target != null) {//发送账户判断非空
                        message = new MessageInfo(user.getMemId(), messageToId, user.getMemName(), targetUser.getMemName(), messageText, messageDate);
                    }
                } else {//抵达地址类型是团队账户
                    target = service.getTeamById(messageToId);
                    teamInfo targetTeam = (teamInfo) target;
                    if (target != null) {//发送账户判断非空
                        message = new MessageInfo(user.getMemId(), messageToId, user.getMemName(), targetTeam.getTeamName(), messageText, messageDate);
                    }
                }
            } else {//团队账号发送消息
                teamInfo team= (teamInfo) session.getAttribute("team");
                myId=team.getTeamId();
                if (type == 0) {//抵达地址类型是个人账户
                    target = service.getMemById(messageToId);
                    memInfo targetUser = (memInfo) target;
                    if (target != null) {//发送账户判断非空
                        message = new MessageInfo(team.getTeamId(), messageToId, team.getTeamName(), targetUser.getMemName(), messageText, messageDate);
                    }
                } else {//抵达地址类型是团队账户
                    target = service.getTeamById(messageToId);
                    teamInfo targetTeam = (teamInfo) target;
                    if (target != null) {//发送账户判断非空
                        message = new MessageInfo(team.getTeamId(), messageToId, team.getTeamName(), targetTeam.getTeamName(), messageText, messageDate);
                    }
                }
            }
            if (target!=null) {
                if (service.insertMessage(message) > 0) {
                    if(service.insertSendRecord(message.getMessageId(),myId)>0){
                        if(service.insertReceiveRecord(message.getMessageId(),messageToId)>0) {
                            json.put("code", 200);
                            json.put("msg", "发送成功并且成功插入记录");
                        }else{
                            json.put("code",404);
                            json.put("msg","插入接受记录失败");
                        }
                    }else{
                        json.put("code",404);
                        json.put("msg","插入发送记录失败");
                    }
                } else {
                    json.put("code", 404);
                    json.put("msg", "发送失败，数据库操作0行");
                }
            } else {
                json.put("code", 404);
                json.put("msg", "目标为空，请检查你的发送id");
            }
        } catch (Exception e) {
            json.put("code", 500);
            json.put("msg", e.getMessage());
        }
        return json;
    }

    /**
     * 获取所有收到的消息
     * @return
     */
    @GetMapping("/getReceivedMessage")
    public JSONObject getReceivedMessage(HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        Integer id;
        List<MessageInfo>lists;
        try{
            if("mem".equals(session.getAttribute("type"))){
                id= (Integer) session.getAttribute("mem_id");
            }else{
                id= (Integer) session.getAttribute("team_id");
            }
            lists=service.getReceivedMessagesById(id);
            if(lists!=null) {
                if (lists.size() > 0) {
                    json.put("code", 200);
                    json.put("msg", "查找成功");
                    json.put("data", lists);
                } else {
                    json.put("code", 404);
                    json.put("msg", "消息数为零");
                }
            }else{
                json.put("code",404);
                json.put("msg","id错误，没有找到对应的收件list");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据前端传入messageId来删除消息
     * @param id 消息id
     * @return
     */
    @GetMapping("/deleteReceivedMessage")
    public JSONObject deleteReceivedMessage(Integer id,HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        MessageInfo message;
        Integer temp_id;//现在登录的id
        try{
            if("mem".equals(session.getAttribute("type"))){
                temp_id= (Integer) session.getAttribute("mem_id");
            }else{
                temp_id= (Integer) session.getAttribute("team_id");
            }
            message=service.getMessageById(id);
            if(message!=null){
                if(message.getMessageToId().equals(temp_id)) {
                    if (service.checkExistInReceivedMessage(message.getMessageId(), temp_id) > 0) {
                        if (service.deleteReceiveRecord(message.getMessageId(), temp_id) > 0) {
                            json.put("code", 200);
                            json.put("msg", "删除成功");
                        } else {
                            json.put("code", 404);
                            json.put("msg", "删除失败,数据库操纵0行");
                        }
                    } else {
                        json.put("code", 404);
                        json.put("msg", "收件箱不存在此消息");
                    }
                }else{
                    json.put("code",404);
                    json.put("msg","删除消息权限错误，您可能不是该消息的可操纵人");
                }
            }else{
                json.put("code",404);
                json.put("msg","没有找到该消息");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据id删除发件箱内的消息
     * @param id
     * @param request
     * @return
     */
    @GetMapping("deleteSendMessage")
    public JSONObject deleteSendMessage(Integer id,HttpServletRequest request){
        JSONObject json=new JSONObject();
        MessageInfo message;
        HttpSession session=request.getSession();
        Integer myId;
        try{
            if("mem".equals(session.getAttribute("type"))){
                myId= (Integer) session.getAttribute("mem_id");
            }else{
                myId= (Integer) session.getAttribute("team_id");
            }
            message=service.getMessageById(id);
            if(message!=null) {
                if(message.getMessageFromId().equals(myId)) {
                    if (service.checkExistInSendMessage(message.getMessageId(), myId) > 0) {
                        if (service.deleteSendRecord(id, myId) > 0) {
                            json.put("code", 200);
                            json.put("msg", "删除发信记录成功");
                        } else {
                            json.put("code", 404);
                            json.put("msg", "删除失败，数据库删除0行");
                        }
                    } else {
                        json.put("code", 404);
                        json.put("msg", "发件箱不存在此消息");
                    }
                }else{
                    json.put("code",404);
                    json.put("msg","消息对应ID不正确，请确认您是否是改消息的发件人");
                }
            }else{
                json.put("code",404);
                json.put("msg","您已删除此记录");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 获取所有发信消息
     * @param request
     * @return
     */
    @GetMapping("getSendMessage")
    public JSONObject getSendMessageList(HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        Integer myId;
        List<MessageInfo>lists;
        try{
            if("mem".equals(session.getAttribute("type"))){
                myId= (Integer) session.getAttribute("mem_id");
            }else{
                myId= (Integer) session.getAttribute("team_id");
            }
            lists=service.getSendMessageById(myId);
            if(lists!=null) {
                if (lists.size() > 0) {
                    json.put("code", 200);
                    json.put("msg", "查询发信息成功");
                    json.put("data", lists);
                } else {
                    json.put("code", 404);
                    json.put("msg", "一共找到0条发信息");
                }
            }else{
                json.put("code",404);
                json.put("msg","id错误，数据库没有找到对应消息");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }
}
