package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.MatchAppleInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.RSABean;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import cn.edu.aynu.onlineRegistrationSystem.utils.MailUtils;
import cn.edu.aynu.onlineRegistrationSystem.utils.RSA;
import cn.edu.aynu.onlineRegistrationSystem.utils.RetrievePasswordUtils;
import cn.edu.aynu.onlineRegistrationSystem.utils.VerifyCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class UtilsController {
    @Autowired
    IndexService service;
    @Autowired
    InfoService infoService;
    @Autowired
    RetrievePasswordUtils retrievePasswordUtils;

    HashMap<String,List> memMap = new HashMap<>();
    HashMap<String,List> teamMap = new HashMap<>();
    /**
     * 获取公钥
     * @return
     */
    @GetMapping(value = "/getPublicKey")
    public JSONObject getPublicKey(HttpServletRequest request){
        RSA rsa=null;
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        try {
            rsa=RSA.getInstance();
            RSABean rsaBean=rsa.getRandomKey();
            session.setAttribute("privateKey",rsaBean.getPrivateKey());
            json.put("data",rsaBean.getPublicKey());
        } catch (Exception e) {
            json.put("code","500");
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 获取验证码
     */
    @GetMapping(value = "/getVeifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        VerifyCode code=new VerifyCode();
        code.output(code.getImage(),response.getOutputStream());
        session.setAttribute("code",code.getText());
    }

    /**
     * 设置用户点击获取比赛信息按钮时候转发比赛id和比赛类型
     */
    @GetMapping(value = "/setMatchId")
    public JSONObject setMatchId(Integer matchId,String type, HttpServletRequest request){
        HttpSession session=request.getSession();
        JSONObject jsonObject=new JSONObject();

        try{
            if(matchId!=null&&type!=null){
                session.setAttribute("matchId",matchId);
                session.setAttribute("matchType",type);
                jsonObject.put("code",200);
                jsonObject.put("msg","获取成功");
            }else{
                jsonObject.put("code",400);
                jsonObject.put("msg","请求参数不正确");
            }
        }catch (Exception e) {
            jsonObject.put("code",500);
            jsonObject.put("msg",e.getMessage());
        }
        return jsonObject;
    }
    /**
     * 获取比赛报名信息
     * @return
     */
    @PostMapping(value = "/getMatchInfo")
    public JSONObject getMatchInfo(HttpServletRequest request){
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        Integer matchId;
        String type;
        try{
            matchId=Integer.parseInt(session.getAttribute("matchId").toString());
            type=session.getAttribute("matchType").toString();
            if(type!=null&&matchId!=null){

                if(type.equals("0")){//获取个人比赛列表
                    System.out.println("个人matchId="+matchId+"matchType="+type);
                    List<MatchAppleInfo> list=service.getMatchInfoByMatchIdWithMemInfo(matchId);
                    json.put("code",200);
                    json.put("msg","获取成功");
                    json.put("data",list);
                }else{//获取团队比赛报名信息列表
                    System.out.println("团队matchId="+matchId+"matchType="+type);
                    List<MatchAppleInfo> list=service.getMatchInfoByMatchIdWithTeamInfo(matchId);
                    json.put("code",200);
                    json.put("msg","获取成功");
                    json.put("data",list);
                }
            }else{
                json.put("code",400);
                json.put("msg","没有获取到指定的参数");
            }
        }catch (Exception e){
            json.put("code",500);
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 忘记密码
     * @param mail 邮箱地址
     * @return json字符串
     */
    @PostMapping("/retrievePassword")
    public JSONObject retrievePassword(@RequestParam("mail") String mail,@RequestParam("type") String type) throws Exception {
        if(Integer.valueOf(type) == 0) {
            return retrievePasswordUtils.getRetrievePassword(mail,memMap,type);
        }else{
            return retrievePasswordUtils.getRetrievePassword(mail,teamMap,type);
        }
    }
    /**
     * 验证邮箱链接是否失效
     * @param mail 邮箱
     * @param uuid 唯一标识
     * @return 返回的是状态
     */
    @GetMapping("/effective")
    public JSONObject isTmpEffective(@RequestParam("mail") String mail, @RequestParam("uuid") String uuid, @RequestParam("type") String type) {
        if(Integer.valueOf(type) == 0) {
            return retrievePasswordUtils.isTmpEffective(mail,uuid,memMap);
        } else {
          return retrievePasswordUtils.isTmpEffective(mail,uuid,teamMap);
        }
    }

    @PostMapping("/setNewPassword")
    public JSONObject setNewPassword(@RequestParam("newPassword") String newPassword, @RequestParam("mail") String mail, @RequestParam("uuid") String uuid, @RequestParam("type") String type) {
        if(Integer.valueOf(type) == 0) {
            return retrievePasswordUtils.setNewPassword(newPassword,mail,uuid,memMap,type);
        } else {
            return retrievePasswordUtils.setNewPassword(newPassword,mail,uuid,teamMap,type);
        }
    }




}

