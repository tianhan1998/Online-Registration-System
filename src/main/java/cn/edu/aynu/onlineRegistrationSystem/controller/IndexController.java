package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.*;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import cn.edu.aynu.onlineRegistrationSystem.utils.MessageUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 第二级页面信息
 */
@RestController
//@RequestMapping("/online")
public class IndexController {
    @Autowired
    IndexService service;
    /**
     * 获取所有比赛列表，包括个人比赛和团队比赛
     * @return 返回所有比赛信息的JSON
     */
    @GetMapping(value = "/matchList",produces = "application/json;charset=utf-8")
    public JSONObject getMatchList() {
        JSONObject json = new JSONObject();
        try {
            List<matchInfo> lists = service.getMatchList();
            json.put("data",lists);
            json.put("code",200);
            json.put("msg","查询成功");
        }catch (Exception e){
            json.put("code",500);
            json.put("msg","数据库查询失败"+e.getMessage());
        }
        return json;
    }

    /**
     * 获取当前已经登录账号报名的所有比赛信息
     * @param pn 页码
     * @param length 每页显示记录数
     * @return 已经报名的比赛信息
     */
    @PostMapping(value = "/matchList",produces = "application/json;charset=utf-8")
    public JSONObject getMatchListById(Integer pn, Integer length, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json=new JSONObject();
        HttpSession session =request.getSession();
        // Integer id= (Integer) session.getAttribute("mem_id"); 更改前
        List<matchInfo> matches;
//        System.out.println("我登陆了但是失败了"+id);
        try {
            if ("mem".equals(session.getAttribute("type"))) {
                Integer id= (Integer) session.getAttribute("mem_id");
                matches = service.getMatchListByMemId(id);
            } else {
                Integer id= (Integer) session.getAttribute("team_id");
                matches = service.getMatchListByTeamId(id);
            }
            json.put("code", 200);
            json.put("data", matches);
            json.put("msg", "查询成功");
        }catch (Exception e) {
            json.put("code", 500);
            json.put("msg", "数据库查询失败" + e.getMessage());
        }
        return json;
    }

    /**
     * 根据比赛的id进行比赛报名（如果个人报名账号类型和比赛要求类型不匹配就失败,或者比赛有邀请码且不一致，也加入失败）
     * @param matchId 待报名比赛的Id号
     * @param matchPassword 比赛邀请码，如果数据库没有比赛邀请码则不需填写
     * @return 成功返回成功的JSON信息，失败返回失败的JSON信息
     */
    @PostMapping(value = "/enroll",produces = "application/json;charset=utf-8")
    public JSONObject memEnrollById(Integer matchId,String matchPassword,HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        memInfo user= (memInfo)session.getAttribute("user");
        matchInfo match;
        teamInfo team= (teamInfo) session.getAttribute("team");
        Integer type;
        try{
            switch ((String)session.getAttribute("type")) {
                case "mem":
                    type = 0;
                    break;
                case "team":
                    type = 1;
                    break;
                default:
                    throw new Exception("未知的登陆类型");
            }
            match=service.getMatchInfoById(matchId);
            if(user==null&&team==null){
                json.put("code",404);
                json.put("msg","用户不存在");
            }
            else if(match==null){
                json.put("code",404);
                json.put("msg","比赛不存在");
            }
            else if(type!=Integer.parseInt(match.getMatchMode())){
                json.put("code",404);
                json.put("msg","用户对应的比赛不正确");
            }else if(matchPassword==null||!matchPassword.equals(match.getMatchPassword())){
                json.put("code",404);
                json.put("msg","比赛邀请码不正确");
            }
            else if(type==0){
                if(service.checkExistInMemMatch(user.getMemId(),matchId)==0) {
                        if (service.insertMemMatch(new memMatch(user.getMemId(), matchId)) > 0) {
                            json.put("code", 200);
                            json.put("msg", "报名成功");
                        } else {
                            throw new Exception("插入个人报名失败");
                        }
                }else{
                    json.put("code",404);
                    json.put("msg","您已报名过比赛");
                }
            }else {
                if (service.checkExistInTeamMatch(team.getTeamId(), matchId) == 0) {
                    if (service.insertTeamMatch(new teamMatch(team.getTeamId(), matchId)) > 0) {
                        json.put("code", 200);
                        json.put("msg", "报名成功");
                    } else {
                        throw new Exception("插入队伍报名失败");
                    }
                }else{
                    json.put("code",404);
                    json.put("msg","您已报名过比赛");
                }
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据当前登录团队账号id获取该团队的所有成员信息
     * @return 获取当前登录团队里面所有的成员信息打包返回JSON
     */
    @GetMapping(value = "/member",produces = "application/json;charset=utf-8")
    public JSONObject getTeamMemInfoById(HttpServletRequest request){
        HttpSession session=request.getSession();
        Integer teamId;
        JSONObject json=new JSONObject();
        List<memInfo> lists;
        try {
            if("team".equals(session.getAttribute("type"))) {
                teamId= (Integer) session.getAttribute("team_account");
                List<Integer> ids = service.getMemidsByTeamId(teamId);//service层有异常抛出，不用判断队伍是否存在
                if (ids.size() > 0) {
                    lists = service.getMemInfoByIds(ids);
                    json.put("code", 200);
                    json.put("msg", "查询成功");
                    json.put("data", lists);
                } else {
                    json.put("code", 200);
                    json.put("msg", "队员数为零");
                }
            }else {
                json.put("code",404);
                json.put("msg","当前登录类型错误");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg","查询数据库出错"+e.getMessage());
        }
        return json;
    }

    /**
     * 团队发送邀请消息
     * @param memId 个人账号id
     * @return 返回成功或者失败的JSON信息
     */
    @Transactional
    @PostMapping(value = "/joinTeam",produces = "application/json;charset=utf-8")//TODO 修改该方法
    public JSONObject joinTeamById(Integer memId,HttpServletRequest request){
        HttpSession session=request.getSession();
        JSONObject json=new JSONObject();
        memInfo user;
        Integer teamId;
        teamInfo team;
        List<Integer> ids;//队伍中的人数
        try {
            if("team".equals(session.getAttribute("type"))) {//确认登陆类型
                teamId= (Integer) session.getAttribute("team_id");
                user = service.getMemInfoById(memId);
                team=service.getTeamInfoById(teamId);
                ids = service.getMemidsByTeamId(teamId);//方法内存在队伍是否存在检测，所以不需要if判断
                if (user == null) {
                    json.put("code", 404);
                    json.put("msg", "用户不存在");
                } else if (ids.size() == 3) {
                    json.put("code", 404);
                    json.put("msg", "队伍人数已满");
                } else {
                    if (ids.size() > 0) {
                        for (int i : ids) {
                            if (i == memId) {
                                throw new Exception("用户已加入此队伍");
                            }
                        }
                    }
                    if (service.checkInviteExistByFromAndTo(teamId, memId) == 0) {//判断是否存在邀请（已发送
                        InviteInfo invite=new InviteInfo(teamId,memId);
                        if (service.insertInvite(invite) > 0) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String text = "您有一条来自" + team.getTeamName() + "队伍的邀请，如果您同意，请点击同意链接，否则请点击拒绝链接\r\n同意链接:http://localhost:8080/ORS/acceptInvite?Id="+invite.getInviteId()+"\r\n拒绝连接:http://localhost:8080/ORS/denyInvite?Id="+invite.getInviteId();
                            MessageInfo newMessage = new MessageInfo(teamId, memId, team.getTeamName(), user.getMemName(), text, simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                            if (MessageUtils.sendMessage(newMessage)) {
                                if(service.updateInviteMessageId(invite,newMessage.getMessageId())>0){
                                    json.put("code", 200);
                                    json.put("msg", "发送邀请成功");
                                }else{
                                    json.put("code",404);
                                    json.put("msg","发送邀请成功，但消息与invite连接失败");
                                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                }
                            } else {
                                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                                json.put("code", 404);
                                json.put("msg", "发送邀请消息失败");
                            }
                        } else {
                            json.put("code", 404);
                            json.put("msg", "插入invite数据库失败");
                        }
                    }else{
                        json.put("msg","您已发过邀请");
                        json.put("code",404);
                    }
                }
            }else{
                json.put("code",404);
                json.put("msg","当前登录类型错误");
            }
        }catch(Exception e){
            json.put("code",500);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            json.put("msg","数据库错误"+e.getMessage());
        }
        return json;
    }

    /**
     * 通过邀请id接受邀请（有账号验证）
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/acceptInvite")
    public JSONObject acceptInvite(Integer id,HttpServletRequest request){
        HttpSession session=request.getSession();
        JSONObject json=new JSONObject();
        teamInfo team;
        InviteInfo invite;
        try{
            if("mem".equals(session.getAttribute("type"))) {
                Integer memId= (Integer) session.getAttribute("mem_id");
                invite = service.checkInviteExistById(id);
                if (invite != null) {
                    if(invite.getInviteToId().equals(memId)){
                        team=service.getTeamInfoById(invite.getInviteFromId());
                        List<Integer> ids=service.getMemidsByTeamId(team.getTeamId());
                        if(ids!=null&&ids.size()==3){
                            json.put("code",404);
                            json.put("msg","此队伍已满");
                            service.deleteInviteById(id);
                        }else {
                            if (service.joinTeamByid(team, memId) > 0) {
                                json.put("code", 200);
                                json.put("msg", "加入成功");
                                service.deleteInviteById(id);
                            } else {
                                json.put("code", 404);
                                json.put("msg", "加入失败，数据库操作0行");
                            }
                        }
                    }else{
                        json.put("code",404);
                        json.put("msg","这不是您的邀请信息");
                    }
                }else{
                    json.put("code",404);
                    json.put("msg","没有此邀请,请检查您的id");
                }
            }else{
                json.put("code",404);
                json.put("msg","登录账号类型错误");
            }
        }catch (Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 通过id拒绝邀请（有身份验证）
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/denyInvite")
    public JSONObject denyInvite(Integer id,HttpServletRequest request){
        HttpSession session=request.getSession();
        JSONObject json=new JSONObject();
        InviteInfo invite;
        try {
            if ("mem".equals(session.getAttribute("type"))) {
                Integer memId = (Integer) session.getAttribute("mem_id");
                invite = service.checkInviteExistById(id);
                if (invite != null) {
                    if (invite.getInviteToId().equals(memId)) {
                        if(service.deleteInviteById(id)>0){
                            json.put("code",200);
                            json.put("msg","删除成功");
                        }else{
                            json.put("code",404);
                            json.put("msg","删除失败，数据库操作0行");
                        }
                    }else{
                        json.put("code",404);
                        json.put("msg","您不是该消息的拥有者");
                    }
                }else{
                    json.put("code",404);
                    json.put("msg","消息id错误");
                }
            }else{
                json.put("code",404);
                json.put("msg","登录账号类型错误");
            }
        }catch (Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 获取当前登录用户的信息
     * @param request
     * @return
     */
    @GetMapping(value = "/getOnlineInfo",produces = "application/json;charset=utf-8")//TODO 新增方法
    public JSONObject getOnlineInfo(HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        Integer myId;
        memInfo mem;
        teamInfo team;
        TeamMessage message=null;
        try{
            if("mem".equals(session.getAttribute("type"))){
                myId= (Integer) session.getAttribute("mem_id");
                mem=service.getMemInfosWithOutPasswordById(myId);
                if(mem!=null){
                    json.put("code",200);
                    json.put("msg","查找当前登录用户成功");
                    json.put("data",mem);
                }else{
                    json.put("code",404);
                    json.put("msg","没有找到用户");
                    json.put("data",mem);
                }
            }else{
                myId= (Integer) session.getAttribute("team_id");
                team=service.getTeamInfoById(myId);
                if(team!=null){
                    message=new TeamMessage(team);
                    json.put("code",200);
                    json.put("msg","查找当前登录用户成功");
                    json.put("data",message);
                }else{
                    json.put("code",404);
                    json.put("msg","没有找到用户");
                    json.put("data",message);
                }
            }
        }catch (Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 获取当前登录用户的所有被邀请的消息
     * @param request
     * @return
     */
    @GetMapping(value = "/getInvitedMessage",produces = "application/json;charset=utf-8")//TODO 新增方法
    public JSONObject getBeInvitedMessage(HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        Integer myId;
        List<MessageInfo>lists;
        try{
            if("mem".equals(session.getAttribute("type"))){
                myId= (Integer) session.getAttribute("mem_id");
                lists=service.selectListBeInvited(myId);
                if(lists!=null){
                    json.put("code",200);
                    json.put("msg","找到所有被邀请消息");
                    json.put("data",lists);
                }else{
                    json.put("code",404);
                    json.put("msg","没有找到任何被邀请消息");
                    json.put("data",new ArrayList<>());
                }
            }else {
                json.put("code",404);
                json.put("msg","当前登录状态错误");
                json.put("data",new ArrayList<>());
            }
        }catch (Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
            json.put("data",new ArrayList<>());
        }
        return json;
    }

}
