package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.ManageSystemService;
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
    public JSONObject getMemList(Integer pn,Integer length,HttpServletRequest request){
        HttpSession session=request.getSession();
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
    @PostMapping("/updateMem")//TODO 新增实现
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
    public JSONObject deleteMem(Integer memId){

        return null;
    }

    /**
     * 获取所有的团队信息
     * @param pn 页码
     * @param length 每页显示的个数
     * @return
     */
    public JSONObject getTeamList(Integer pn,Integer length){

        return null;
    }

    /**
     * 根据团队账号id修改除了账号和队员的所有信息
     * @param id 团队id
     * @param name 团队修改后名字
     * @param password 团队修改后密码
     * @param email 团队修改后email
     * @return
     */
    public JSONObject modefTeamInfo(Integer id,String name,String password,String email){

        return null;
    }

    /**
     * 根据id删除该团队
     * @param id 团队账号id
     * @return
     */
    public JSONObject deleteTeam(Integer id){

        return null;
    }

    /**
     * 根据传入的比赛id获取所有参赛人员基本信息列表，
     * 团队赛获取团队名和队员姓名+学号+性别
     * 个人赛获取学号+姓名+性别
     * @param id 比赛表id
     * @return
     */
    public JSONObject getMatchInfoList(Integer id){

        return null;
    }

}
