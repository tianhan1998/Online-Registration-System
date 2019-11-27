package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.TeamMessage;
import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import cn.edu.aynu.onlineRegistrationSystem.service.ManageSystemService;
import cn.edu.aynu.onlineRegistrationSystem.utils.SchedulingUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台管理系统
 */

@RestController
@RequestMapping("/admin")//方便过滤器筛选
@PropertySource("classpath:admin.properties")
public class ManageSystemController {

    @Autowired
    ManageSystemService service;
    @Autowired
    IndexService indexService;

    @Value("${admin.account}")
    String adminac;
    @Value("${admin.password}")
    String adminps;
    /**
     * 后台管理员登陆判断页面，账号密码写入配置文件中
     * @param account 账号
     * @param password 密码
     * @return
     */
    @GetMapping(value = "/login",produces = "application/json;charset=utf-8")
    public JSONObject loginIn(String account, String password, HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        if(adminac.equals(account)&&adminps.equals(password)){
            json.put("code",200);
            json.put("msg","管理员登陆成功");
            session.setAttribute("type","admin");
        }else{
            json.put("code",404);
            json.put("msg","用户名或密码错误");
        }
        return json;
    }

    /**
     * 获取所有个人账号所有信息
     * @param pn 页码
     * @param length 每页显示个数
     * @return
     */
    @GetMapping("/memLists")
    public JSONObject getMemList(Integer pn,Integer length){
        JSONObject json=new JSONObject();
        List<memInfo> lists;
        try {
            lists = service.getMemInfoLists();
            if (lists != null) {
                json.put("code", 200);
                json.put("msg", "查询成功");
                json.put("data", lists);
            } else {
                json.put("code", 200);
                json.put("msg", "查询成功，但lists为null");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg","数据库查询失败"+e.getMessage());
        }
        return json;
    }

    /**
     * 修改id为${memId}的用户的所有信息除了学号
     * @param memId 学号
     * @param password 修改后的密码
     * @param sex 修改后的性别
     * @param name 修改后的名字
     * @param email 修改后的email
     * @return JSON
     */
    @PostMapping("/updateMem")
    public JSONObject modifyMemInfo(Integer memId,String password,String sex,String name,String email){
        JSONObject json=new JSONObject();
        memInfo user=new memInfo(memId,name,email,sex,password);
        try{
            if(service.updateMem(user)>0){
                json.put("code",200);
                json.put("msg","修改成功");
            }else{
                json.put("code",200);
                json.put("msg","修改失败，数据库未含有此用户");
            }
        }catch (Exception e){
            json.put("msg","数据库修改失败"+e.getMessage());
            json.put("code",500);
        }
        return json;
    }

    /**
     * 删除学号为${memId}的个人账号
     * @param memId
     * @return
     */
    @GetMapping("/deleteMem")
    public JSONObject deleteMem(Integer memId){
        JSONObject json=new JSONObject();
        try {
            if(service.deleteMem(memId)>0){
                json.put("code",200);
                json.put("msg","删除成功");
            }else{
                json.put("code",404);
                json.put("msg","未找到此用户");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 获取所有的团队信息+团队名字
     * @param pn 页码
     * @param length 每页显示的个数
     * @return
     */
    @GetMapping("/teamLists")
    public JSONObject getTeamList(Integer pn,Integer length){
        List<teamInfo>lists;
        JSONObject json=new JSONObject();
        List<TeamMessage> list=new ArrayList<>();
        try{
            lists=service.getTeamList();
            if(lists.size()>0){
                for(teamInfo temp:lists) {
                    List<Integer> tempIds=service.getMemidsByTeamId(temp.getTeamId());
                    if(tempIds.size()>0){
                        List<memInfo> tempUsers=service.getMemListsByIds(tempIds);
                        TeamMessage tempTeamMes=new TeamMessage(temp,tempUsers);
                        list.add(tempTeamMes);
                    }else{//队伍中无人
                        TeamMessage teamMessage=new TeamMessage(temp);
                        list.add(teamMessage);
                    }
                }
                json.put("code",200);
                json.put("msg","查询成功");
                json.put("data",list);
            }else{
                json.put("code",200);
                json.put("data",list);
                json.put("msg","未找到任何队伍");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据团队账号id修改除了账号和队员的所有信息
     * @param id 团队id
     * @param name 团队修改后名字
     * @param password 团队修改后密码
     * @param email 团队修改后email
     * @return
     */
    @PostMapping("/updateTeam")
    public JSONObject modelTeamInfo(Integer id,String name,String password,String email){
        JSONObject json=new JSONObject();
        try{
            teamInfo team=new teamInfo(id,name,email,password);
            if(service.updateTeam(team)>0){
                json.put("code",200);
                json.put("msg","修改队伍信息成功");
            }else{
                json.put("code",404);
                json.put("msg","修改队伍失败,未找到该队伍");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据id删除该团队
     * @param id 团队账号id
     * @return
     */
    @GetMapping("deleteTeam")
    public JSONObject deleteTeam(Integer id){
        JSONObject json=new JSONObject();
        try{
            if(service.deleteTeam(id)>0){
                json.put("code",200);
                json.put("msg","删除队伍成功");
            }else{
                json.put("code",404);
                json.put("msg","未找到该队伍");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据传入的比赛id获取所有参赛人员基本信息列表，
     * 团队赛获取团队名和队员姓名+学号+性别
     * 个人赛获取学号+姓名+性别
     * @param id 比赛表id
     * @return
     */
    @GetMapping("/enrolledTeamsOrMems")
    public JSONObject getMatchInfoList(Integer id){
        JSONObject json=new JSONObject();
        matchInfo match;
        List<Integer> ids;//参加比赛的个人或团队id组
        List<memInfo> users;//参加比赛的个人组
        List<TeamMessage> teamMessages=new ArrayList<>();//参加所有团队比赛的团队信息
        try{
            match=service.getMatch(id);
            if(match!=null){
                if(Integer.parseInt(match.getMatchMode())==0){//个人比赛
                    ids=service.getJoinedMemIdsByMatchId(match.getMatchId());
                    if(ids.size()>0){
                        users=service.getMemListsByIds(ids);
                        json.put("code",200);
                        json.put("msg","找到参加此个人比赛的所有用户");
                        json.put("data",users);
                    }else{
                        json.put("code",404);
                        json.put("data",new ArrayList<>());
                        json.put("msg","没有找到任何用户参加此比赛");
                    }
                }else {//团队比赛
                    ids = service.getJoinedTeamIdsByMatchId(match.getMatchId());
                    if (ids.size() > 0) {
                        for (Integer temp_id : ids) {
                            teamInfo temp_team = service.getTeamByTeamId(temp_id);
                            List<Integer> temp_mem_ids = service.getMemidsByTeamId(temp_id);
                            if(temp_mem_ids.size()>0) {//队伍有人
                                List<memInfo> temp_mems = service.getMemListsByIds(temp_mem_ids);
                                TeamMessage message = new TeamMessage(temp_team, temp_mems);
                                teamMessages.add(message);
                            }else{//空队伍参加
                                TeamMessage message=new TeamMessage(temp_team);
                                teamMessages.add(message);
                            }
                        }
                        json.put("code",200);
                        json.put("msg","找到参加此团队比赛的所有用户");
                        json.put("data",teamMessages);
                    }else{
                        json.put("code",404);
                        json.put("data",teamMessages);
                        json.put("msg","没有找到任何队伍参加此比赛");
                    }
                }
            }else{
                json.put("code",404);
                json.put("msg","未找到该比赛");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 添加新的比赛
     * @param matchTitle 比赛标题
     * @param matchStarTime 比赛开始时间
     * @param matchEndTime 比赛结束时间
     * @param matchMode 比赛模式
     * @param matchPassword 比赛邀请码
     * @return json
     */
    @PostMapping(value = "/putMatch")
    public JSONObject addMatch(String matchTitle,String matchStarTime,String matchEndTime,String matchMode,String matchPassword){
        JSONObject json=new JSONObject();

        try {
            int rel=service.addMatch(matchTitle,matchStarTime,matchEndTime,matchMode,matchPassword);
            if(rel!=0){
                SchedulingUtils.startList =null;//清空比赛检查队列
                json.put("code",200);
                json.put("msg","添加成功");
            }else{
                json.put("code",400);
                json.put("msg","添加失败成功了"+rel+"条数据");
            }
        } catch (Exception e) {
            json.put("code",500);
            System.out.println(e);
            json.put("msg",e.getMessage());
        }

        return json;
    }
    /**
     * 获取所有比赛列表，包括个人比赛和团队比赛
     * @return 返回所有比赛信息的JSON
     */
    @GetMapping(value = "/matchList",produces = "application/json;charset=utf-8")
    public JSONObject getMatchList() {
        JSONObject json = new JSONObject();
        try {
            List<matchInfo> lists = indexService.getMatchList();
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
     * 通过id删除比赛
     * @param id
     * @return
     */
    @GetMapping("/deleteMatch")//TODO 新增实现
    public JSONObject deleteMatch(Integer id){
        JSONObject json=new JSONObject();
        try{
            if(service.deleteMatch(id)>0){
                json.put("code",200);
                json.put("msg","删除比赛成功");
            }else{
                json.put("code",404);
                json.put("msg","删除比赛失败,数据库操纵0行");
            }
        }catch (Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

}
