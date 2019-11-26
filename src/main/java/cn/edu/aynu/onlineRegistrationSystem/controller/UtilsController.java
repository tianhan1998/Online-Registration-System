package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.MatchAppleInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.RSABean;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import cn.edu.aynu.onlineRegistrationSystem.utils.MailUtils;
import cn.edu.aynu.onlineRegistrationSystem.utils.RSA;
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
    MailUtils mailUtils;
    @Autowired
    InfoService infoService;

    HashMap<String,List> map = new HashMap<>();

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

    @PostMapping(value = "/retrievePassword")
    public JSONObject getRetrievePassword(String mail) throws Exception {

        JSONObject jsonObject = new JSONObject();
        List list = new ArrayList();
        System.out.println(mail);
        if(mail==null) {
            jsonObject.put("code","400");
            jsonObject.put("msg","邮箱为空!");
        }else{
            boolean flag = infoService.memRetrievePassword(mail);
            if(flag){
                long tmp = System.currentTimeMillis();
                //随机生成字符串作为唯一标识
                String uuid = UUID.randomUUID().toString().replaceAll("-","")+UUID.randomUUID().toString().replaceAll("-","");
                //如果之前存在忘记密码，则重新修改密码
                if(map.get(mail)!=null) {
                    Long s = (System.currentTimeMillis() - (long)map.get(mail).get(0)) / (1000 * 60);
                    System.out.println(s);
                    //最少间隔5分钟
                    if(s<=5){
                        jsonObject.put("code","402");
                        jsonObject.put("msg","请求时间过短，请隔一段时间再发送邮件！");
                        return jsonObject;
                    }
                    list.add(tmp);
                    list.add(uuid);
                    map.replace(mail,list);
                }else{
                    list.add(tmp);
                    list.add(uuid);
                    map.put(mail,list);
                }
                String h = "http://localhost:8080/ORS/setNewPassowrd?mail="+mail+"&uuid="+uuid;
                mailUtils.sendMail(mail,"报名系统","<h1>请点击下面链接修改密码</h1><a href=\'"+h+"\'>"+h+"</a>");
                jsonObject.put("code","200");
                jsonObject.put("msg","发送成功!");
            }else{
                jsonObject.put("code","404");
                jsonObject.put("msg","没有找到邮箱！");
            }
        }
        return jsonObject;
    }
    @PostMapping("/setNewPassword")
    public JSONObject setNewPassword(@RequestParam("newPassword") String newPassword, @RequestParam("mail") String mail, @RequestParam("uuid") String uuid) {
        //TODO 点击邮件中的地址验证成功后就可以修改密码了，然后下面的还没写
        System.out.println(newPassword);
        System.out.println(mail);
        System.out.println(uuid);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","200");
        jsonObject.put("msg","修改成功");
        return jsonObject;
    }
}

