package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.MatchAppleInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import cn.edu.aynu.onlineRegistrationSystem.service.SignService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 获取第三级页面更详细的信息
 */

@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;
    @Autowired
    SignService signService;


    /**
     * 根据传入的学号返回详细信息
     * @param memId 学号
     * @param match_id 比赛id
     * @return 返回JSON详细信息，如果有成绩就返回成绩，没有成绩成绩一栏返回null
     */
    @RequestMapping("/getMemInfo")
    public JSONObject getMemInfo(Integer memId, Integer match_id){

        JSONObject jsonObject = new JSONObject();
        /**
         * type: mem是个人账号，team是团队账号
         * 个人账号就查询mem_info的所有信息
         * 团队账号就查询这个团队所有人的信息和这个比赛信息
         */


        try{
            List<Object> list = infoService.getMemScore(memId, match_id);
            jsonObject.put("code","200");
            jsonObject.put("msg","成功");
            jsonObject.put("data",list);

        }catch (Exception e) {
            jsonObject.put("code","500");
            jsonObject.put("msg","数据库错误"+e.getMessage());
        }
        return  jsonObject;

    }

    /**
     * 根据传入的团队账号返回团队的所有信息+成绩和所有成员的基本信息（学号，姓名，个人成绩）
     * 成绩没有，则成绩哪一栏返回null
     * @param teamId 团队id
     * @param match_id 比赛的id
     * @return 返回JSON信息，如果有成绩就返回成绩，没有成绩成绩一栏返回null
     */
    @RequestMapping("/getTeamInfoAndmemInfo")
    public JSONObject getTeamInfoAndmemInfo(Integer teamId, Integer match_id){

        JSONObject jsonObject = new JSONObject();

        try{
            List<Object> list = infoService.getTeamScore(teamId,match_id);
            jsonObject.put("code","200");
            jsonObject.put("msg","成功");
            jsonObject.put("data",list);
        }catch (Exception e) {
            jsonObject.put("code","500");
            jsonObject.put("msg","数据库错误"+e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 获取当前登录用户的个人信息
     * @return
     */
    @GetMapping(value = "/getInfo") //TODO 新增方法
    public JSONObject getInfo(HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        try{
            String type=session.getAttribute("type").toString();
            if(type.equals("mem")==true){//获取个人信息
                Integer myId= (Integer)session.getAttribute("mem_id");
                memInfo mem=infoService.getMemInfo(myId);
                if (mem!=null) {
                    mem.setMemPassword("null");
                    json.put("code",200);
                    json.put("msg","获取个人信息成功");
                    json.put("data",mem);
                }else{
                    json.put("code",400);
                    json.put("msg","获取个人信息失败");
                    json.put("data",mem);
                }
            }else{//获取团队的部分信息
                Integer myId= (Integer)session.getAttribute("team_id");
                teamInfo mem=infoService.getTeamInfo(myId);
                if (mem!=null) {
                    json.put("code",200);
                    mem.setTeamPassword("null");
                    mem.setTeamId(-1);
                    mem.setTeamAccount("-1");
                    mem.setTeamEmail("-1");
                    json.put("msg","获取团队信息成功");
                    json.put("data",mem);
                }else{
                    json.put("code",400);
                    json.put("msg","获取团队信息失败");
                    json.put("data",mem);
                }
            }
        }catch (Exception e)
        {
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 根据id修改团队姓名名字//TODO 新增方法
     * @param teamName 修改后的姓名
     * @return
     */
    @PostMapping(value = "/setTeamInfo")
    public JSONObject setTeamInfo(String teamName,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();

        HttpSession session=request.getSession();
        try {
            Integer id=(Integer) session.getAttribute("team_id");
            teamInfo teamInfo=new teamInfo();
            teamInfo.setTeamName(teamName);
            int rel=infoService.setTeamInfo(id,teamInfo);
            if(rel!=0){//修改成功
                jsonObject.put("code",200);
                jsonObject.put("msg","修改成功");
            }else{//修改失败
                jsonObject.put("code",400);
                jsonObject.put("msg","修改失败,请重新登录稍后在尝试");
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            jsonObject.put("msg",e.getMessage());
        }

        return jsonObject;
    }

    /**
     * 根据id修改个人信息 //TODO 新增方法
     * @param memName 修改后的姓名
     * @param memSex 修改后的性别
     * @return
     */
    @PostMapping(value = "/setMemmInfo")
    public JSONObject setMemInfo(String memSex,String memName,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        try {
            HttpSession session=request.getSession();
            Integer id=(Integer) session.getAttribute("mem_id");
            memInfo memInfo=new memInfo();
            memInfo.setMemSex(memSex);
            memInfo.setMemName(memName);
            int rel=infoService.setMemInfo(id,memInfo);
            if(rel!=0){//修改成功
                jsonObject.put("code",200);
                jsonObject.put("msg","修改成功");
            }else{//修改失败
                jsonObject.put("code",400);
                jsonObject.put("msg","修改失败,请重新登录稍后在尝试");
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            jsonObject.put("msg",e.getMessage());
        }

        return jsonObject;
    }

    /**
     * 获取当前个人账号加入团队的列表 //TODO 获取这个人加入过的团队列表
     * @return
     */
    @GetMapping(value = "/getJoinTeamList")
    public JSONObject getJoinTeamInfoList(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        HttpSession session=request.getSession();
        try{
            if(session.getAttribute("type").toString().equals("mem")) {//个人账号
                Integer id = (Integer) session.getAttribute("mem_id");
                if (id != null) {
                    List<MatchAppleInfo> list = infoService.getJoinTeamInfoList(id);
                    jsonObject.put("code", 200);
                    jsonObject.put("msg", "获取成功");
                    jsonObject.put("data", list);
                }
            }else {
                jsonObject.put("code", 400);
                jsonObject.put("msg", "你不是个人账号");
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 修改个人和团队的密码
     * @param lastPassword 上次密码
     * @param newPassword 新的密码
     * @return
     */
    @PostMapping(value = "/setPassword")//TODO 修改密码
    public JSONObject setPassword(String lastPassword,String newPassword,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        HttpSession session=request.getSession();
        try{
            if(session.getAttribute("type").toString().equals("mem")){//个人账号
                Integer id=(Integer)session.getAttribute("mem_id");
                if(signService.signInMem(id, lastPassword).size()!=0){//验证密码通过
                    int rel=infoService.setMemPassword(id,newPassword);
                    if(rel!=0){
                        jsonObject.put("code",200);
                        jsonObject.put("msg","修改成功");
                    }else{
                        jsonObject.put("code",400);
                        jsonObject.put("msg","密码未成功修改请刷新页面重试");
                    }
                }else{
                    jsonObject.put("code",400);
                    jsonObject.put("msg","原密码错误");
                }
            }else{//团队账号

                Integer id=(Integer)session.getAttribute("team_id");
                String accound=session.getAttribute("team_account").toString();
                if(signService.signInTeam(accound, lastPassword).size()!=0){//验证密码通过
                    int rel=infoService.setTeamPassword(id,newPassword);
                    if(rel!=0){
                        jsonObject.put("code",200);
                        jsonObject.put("msg","修改失败");
                    }else{
                        jsonObject.put("code",400);
                        jsonObject.put("msg","密码未成功修改请刷新页面重试");
                    }
                }else{
                    jsonObject.put("code",400);
                    jsonObject.put("msg","原密码错误");
                }
            }
        }catch (Exception e){
            jsonObject.put("code",500);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }

}
