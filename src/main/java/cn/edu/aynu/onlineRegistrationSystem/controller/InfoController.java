package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
