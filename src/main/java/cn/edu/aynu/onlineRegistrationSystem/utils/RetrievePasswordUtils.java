package cn.edu.aynu.onlineRegistrationSystem.utils;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author wys
 * @date 2019/11/27
 */
@Component
public class RetrievePasswordUtils {

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private InfoService infoService;

    public JSONObject getRetrievePassword(String mail, Map<String,List> map, String type) throws Exception {

        JSONObject jsonObject = new JSONObject();
        List list = new ArrayList();
        System.out.println(mail);
        if(mail==null) {
            jsonObject.put("code","400");
            jsonObject.put("msg","邮箱为空!");
        }else{
            memInfo flag = infoService.memRetrievePassword(mail);
            if(flag!=null){
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
                String h = "https://47.75.143.53/:8080/ORS/setNewPassowrd?mail="+mail+"&uuid="+uuid+"&type="+type;
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

    public JSONObject setNewPassword(String newPassword, String mail, String uuid, Map<String,List> map, String type) {
        System.out.println(newPassword);
        System.out.println(mail);
        System.out.println(uuid);
        JSONObject jsonObject = new JSONObject();
        //验证一下时间戳是否在有效范围内
        long tmp = System.currentTimeMillis();
        if(map.get(mail)!=null) {
            Long s = (System.currentTimeMillis() - (long)map.get(mail).get(0)) / (1000 * 60);
            if(s>=15) {
                jsonObject.put("code","402");
                jsonObject.put("msg","当前链接失效！");
            } else {
                //修改密码
                try{
                    if(type.equals("0")) {
                        memInfo mem = infoService.memRetrievePassword(mail);
                        if(mem == null) {
                            jsonObject.put("code","401");
                            jsonObject.put("msg","找不到用户！");
                        } else {

                            infoService.setMemPassword(mem.getMemId(),newPassword);
                            jsonObject.put("code","200");
                            jsonObject.put("msg","修改成功！");
                            map.remove(mail);
                        }
                    } else {
                        teamInfo team = infoService.teamRetrievePassword(mail);
                        if(team == null) {
                            jsonObject.put("code","401");
                            jsonObject.put("msg","找不到团队!");
                        } else {
                            infoService.setTeamPassword(team.getTeamId(),newPassword);
                            jsonObject.put("code","200");
                            jsonObject.put("msg","修改成功！");
                            map.remove(mail);
                        }
                    }
                }catch (Exception e) {
                    jsonObject.put("code","400");
                    jsonObject.put("msg","密码重置失败");
                }

            }
        }else{
            jsonObject.put("code","402");
            jsonObject.put("msg","当前链接失效！");
        }
        return jsonObject;
    }

    public JSONObject isTmpEffective(String mail, String uuid, Map<String,List> map) {
        JSONObject jsonObject = new JSONObject();
        if(map.get(mail)!=null) {
            Long s = (System.currentTimeMillis() - (long)map.get(mail).get(0)) / (1000 * 60);
            if(s>=15) {
                jsonObject.put("code","402");
                //失效就给他移除
                map.remove("mail");
            }else{
                if(map.get(mail).get(1).equals(uuid)){
                    jsonObject.put("code","200");
                }else{
                    jsonObject.put("code","402");
                }
            }
        }else{
            jsonObject.put("code","404");
        }
        return jsonObject;
    }

}
