package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.MessageInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
 * company: www.abc.com
 * Author: Administrator
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
        Integer myid;
        try {
            if ("mem".equals(session.getAttribute("type"))) {//个人账号发送消息
                memInfo user = (memInfo) session.getAttribute("user");
                myid=user.getMemId();
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
                myid=team.getTeamId();
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
                    if(service.insertSendRecord(message.getMessageId(),myid)>0){
                        if(service.insertReceiveRecord(message.getMessageId(),myid)>0) {
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
                    json.put("msg", 404);
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
    @GetMapping("/getMessage")
    public JSONObject getMessage(HttpServletRequest request){
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
            lists=service.getMessagesById(id);
            if(lists.size()>0){
                json.put("code",200);
                json.put("msg","查找成功");
                json.put("data",lists);
            }else{
                json.put("code",404);
                json.put("msg","消息数为零");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据前端传入messageId来删除消息
     * @param id
     * @return
     */
    @GetMapping("/deleteMessage")
    public JSONObject deleteMessage(Integer id,HttpServletRequest request){
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
                if(message.getMessageToId()==temp_id){
                    if(service.deleteReceiveRecord(message.getMessageId(),temp_id)>0){
                        json.put("code",200);
                        json.put("msg","删除成功");
                    }else{
                        json.put("code",404);
                        json.put("msg","删除失败,数据库操纵0行");
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
}
