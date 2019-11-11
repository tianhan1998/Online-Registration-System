package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.*;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 第二级页面信息
 */
@RestController
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
     * 获取当前已经登录账号报名的所有比赛信息//TODO 修改参数
     * @param pn 页码
     * @param length 每页显示记录数
     * @return 已经报名的比赛信息
     */
    @PostMapping(value = "/matchList",produces = "application/json;charset=utf-8")
    public JSONObject getMatchListById(Integer pn, Integer length, HttpServletRequest request, HttpServletResponse response) {
        JSONObject json=new JSONObject();
        HttpSession session =request.getSession();
        Integer id= (Integer) session.getAttribute("mem_id");
        List<matchInfo> matches;
        try {
            if(id==null){//判断登录态
                response.sendRedirect("login");
                return null;
            }
            if ("mem".equals(session.getAttribute("type"))) {
                matches = service.getMatchListByMemId(id);
            } else {
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
     * 根据传入的学号和比赛的id进行比赛报名（如果个人报名账号类型和比赛要求类型不匹配就失败,或者比赛有邀请码且不一致，也加入失败）
     * @param memId 学号
     * @param matchId 待报名比赛的Id号
     * @param type 0是个人账号，1团队账号
     * @param matchPassword 比赛邀请码，如果数据库没有比赛邀请码则不需填写
     * @param pn 页码
     * @param length 每页显示记录数
     * @return 成功返回成功的JSON信息，失败返回失败的JSON信息
     */
    @PostMapping(value = "/enroll",produces = "application/json;charset=utf-8")
    public JSONObject memEnrollById(Integer memId,Integer matchId,Integer type,String matchPassword,Integer pn,Integer length){
        JSONObject json=new JSONObject();
        memInfo user;
        matchInfo match;
        try{
            user=service.getMemInfoById(memId);
            match=service.getMatchInfoById(matchId);
            if(user==null){
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
            }else if(!matchPassword.equals(match.getMatchPassword())){
                json.put("code",404);
                json.put("msg","比赛邀请码不正确");
            }
            else if(type==0){
                if(service.checkExistInMemMatch(memId,matchId)==0) {
                    if (service.insertMemMatch(new memMatch(memId, matchId)) > 0) {
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
                if (service.checkExistInTeamMatch(memId, matchId) == 0) {
                    if (service.insertTeamMatch(new teamMatch(memId, matchId)) > 0) {
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
            json.put("msg","数据库错误"+e.getMessage());
        }
        return json;
    }

    /**
     * 根据团队账号id获取该团队的所有成员信息
     * @param teamId 团队账号id //TODO 修改参数matchId为teamId
     * @return 获取id为${id}这个团队里面所有的成员信息打包返回JSON
     */
    @GetMapping(value = "/member/{teamId}",produces = "application/json;charset=utf-8")
    public JSONObject getTeamMemInfoById(@PathVariable("teamId") Integer teamId){
        JSONObject json=new JSONObject();
        List<memInfo> lists;
        try {
            List<Integer> ids = service.getMemidsByTeamId(teamId);//service层有异常抛出，不用判断队伍是否存在
            if(ids.size()>0) {
                lists=service.getMemInfoByIds(ids);
                json.put("code", 200);
                json.put("msg", "查询成功");
                json.put("data", lists);
            }else{
                json.put("code",200);
                json.put("msg","队员数为零");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg","查询数据库出错"+e.getMessage());
        }
        return json;
    }

    /**
     * 根据传入团队账号id和个人账号id让个人账号加入这个团队账号
     * 如果团队人数到达上限则加入失败，如果个人账号不存在则加入失败 如果这个人已经加入这个团队，则加入失败
     * @param teamId 团队账号id //TODO 修改参数matchId为teamId
     * @param memId 个人账号id
     * @return 返回成功或者失败的JSON信息
     */
    @PostMapping(value = "/joinTeam",produces = "application/json;charset=utf-8")
    public JSONObject joinTeamById(Integer teamId,Integer memId){
        JSONObject json=new JSONObject();
        teamInfo team;
        memInfo user;
        List<Integer> ids;//队伍中的人数
        try {
            user=service.getMemInfoById(memId);
            team=service.getTeamInfoById(teamId);
            ids=service.getMemidsByTeamId(teamId);//方法内存在队伍是否存在检测，所以不需要if判断
            if(user==null){
                json.put("code",404);
                json.put("msg","用户不存在");
            }else if(ids.size()==3){
                json.put("code",404);
                json.put("msg","队伍人数已满");
            }else {
                if (ids.size() > 0) {
                    for(int i:ids){
                        if(i==memId) {
                            throw new Exception("您已加入此队伍");
                        }
                    }
                }
                if (service.joinTeamByid(team, memId) > 0) {
                    json.put("code", 200);
                    json.put("msg", "加入队伍成功");
                } else {
                    json.put("code", 404);
                    json.put("msg", "加入队伍失败");
                }
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg","数据库错误"+e.getMessage());
        }
        return json;
    }

}
