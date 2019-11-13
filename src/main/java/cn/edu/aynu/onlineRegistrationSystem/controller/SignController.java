package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.SignService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * company: cn.edu.aynu
 * Author: 算法艺术社
 * Create Data: 2019/11/3 0003
 */
@RestController
public class SignController {
    @Autowired
    SignService service;


    @Autowired
    JSONObject json;
    /**
     *个人注册
     * @param memId 学号
     * @param memName 姓名
     * @param memPassowrd 密码
     * @param memEmail 安全邮箱
     * @return json{code:200,msg:xxx,data:[]}
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public JSONObject signUp(String memId, String memName, String memPassowrd, String memEmail, String memSex, HttpServletRequest request)  {

        memInfo memInfo = new memInfo(Integer.valueOf(memId),memName,memEmail,"M",memPassowrd);
        if(service.singUp(memInfo)){
            HttpSession session=request.getSession();
            session.setAttribute("mem_id",memId);
            session.setAttribute("type","mem");
            String obj = JSONObject.toJSONString(memInfo);
            json.put("code",200);
            json.put("msg","注册成功");
            json.put("data",obj);
        }else{
            json.put("success",404);
            json.put("msg","用户已存在！");
        }

        return json;
    }

    /***
     *个人注册ajax检测id是否重复
     * @param memId 检查id重复
     * @return json{code:200,msg:"此学号可以注册"}
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.GET)
    public JSONObject checkId(Integer memId){
        JSONObject json=new JSONObject();
        memInfo user;
        try {
            user = service.checkId(memId);
            if(user==null){
                json.put("code",200);
                json.put("msg","此学号可以注册");
            }else{
                json.put("code",200);
                json.put("msg","此学号已被占用");
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg","数据库错误"+e.getMessage());
        }
        return json;
    }

    /**
     * 检测团队账户是否存在
     * @param team_account 前端团队账号
     * @return
     */
    @GetMapping("/signUpTeam")
    public JSONObject checkTeamId(String team_account){
        JSONObject json=new JSONObject();
        teamInfo team;
        try{
            team=service.checkTeamAccount(team_account);
            if(team==null){
                json.put("code",200);
                json.put("msg","此账户可以注册");
            }else{
                json.put("code",200);
                json.put("msg","此账户已经存在");
            }
        }catch (Exception e){
            json.put("code",500);
            json.put("msg","数据库查询失败"+e.getMessage());
        }
        return json;
    }

    /***
     *个人post传入登录表单
     * @param id 前端学号
     * @param team_account 前端团队账号
     * @param password 密码
     * @param type 0是个人，1是团队.
     * @return json{code:200,msg:登陆成功,data:登录用户对象}
     * 登陆成功向session中存入mem_id和用户类型（mem类型）
     */
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public JSONObject signIn(String team_account,String id, String password, HttpServletRequest request,Integer type){
        HttpSession session;
        List<memInfo> list;
        List<teamInfo> lists;
        try {
            session=request.getSession();
            if(type==0) {
                if(id!=null) {
                    list = service.signInMem(Integer.valueOf(id), password);
                    if (list.size() != 0) {
                        memInfo user = list.get(0);
                        session.setAttribute("mem_id", user.getMemId());
                        session.setAttribute("type", "mem");
                        session.setAttribute("user",user);
                        json.put("data", JSONArray.toJSONString(user));
                        json.put("code", 200);
                        json.put("msg", "登陆成功");

                    } else {
                        json.put("code", 404);
                        json.put("msg", "学号不存在或密码错误");
                    }
                }else{
                    json.put("msg","用户名失效");
                    json.put("code",404);
                }
            }else {
                lists = service.signInTeam(team_account, password);
                if (lists.size() > 0) {
                    teamInfo team = lists.get(0);
                    session.setAttribute("team_id", team.getTeamId());
                    session.setAttribute("team_account", team.getTeamAccount());
                    session.setAttribute("type", "team");
                    json.put("code", 200);
                    json.put("msg", "登陆成功");
                    json.put("data", JSONArray.toJSONString(team));
                }else{
                    json.put("code", 404);
                    json.put("msg", "学号不存在或密码错误");
                }
            }
        }catch(Exception e){
            json.put("code",500);
            json.put("msg","数据库查询失败"+e.getMessage());
        }
        return json;
    }


}
