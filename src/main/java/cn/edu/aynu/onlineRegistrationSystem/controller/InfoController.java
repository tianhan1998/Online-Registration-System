package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
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
    @GetMapping(value = "/getInfo")
    public JSONObject getInfo(HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        String type=session.getAttribute("type").toString();

        try{
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
            }else{//获取团队信息
                Integer myId= (Integer)session.getAttribute("team_id");
                teamInfo mem=infoService.getTeamInfo(myId);
                if (mem!=null) {
                    json.put("code",200);
                    mem.setTeamPassword("null");
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
     * 根据id修改团队姓名名字
     * @param teamName 修改后的姓名
     * @return
     */
    @PostMapping(value = "/setTeamInfo")
    public JSONObject setTeamInfo(String teamName,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();

        HttpSession session=request.getSession();
        Integer id=(Integer) session.getAttribute("team_id");
        teamInfo teamInfo=new teamInfo();
        teamInfo.setTeamName(teamName);
        int rel=infoService.setTeamInfo(id,teamInfo);
        if(rel!=0){//修改成功
            jsonObject.put("code","200");
            jsonObject.put("msg","修改成功");
        }else{//修改失败
            jsonObject.put("code","400");
            jsonObject.put("msg","修改失败,请重新登录稍后在尝试");
        }
        return jsonObject;
    }

    /**
     * 根据id修改个人信息
     * @param memName 修改后的姓名
     * @param memSex 修改后的性别
     * @return
     */
    @PostMapping(value = "/setMemmInfo")
    public JSONObject setMemInfo(String memSex,String memName,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();

        HttpSession session=request.getSession();
        Integer id=(Integer) session.getAttribute("mem_id");
        memInfo memInfo=new memInfo();
        memInfo.setMemSex(memSex);
        memInfo.setMemName(memName);
        int rel=infoService.setMemInfo(id,memInfo);
        if(rel!=0){//修改成功
            jsonObject.put("code","200");
            jsonObject.put("msg","修改成功");
        }else{//修改失败
            jsonObject.put("code","400");
            jsonObject.put("msg","修改失败,请重新登录稍后在尝试");
        }
        return jsonObject;
    }
}
