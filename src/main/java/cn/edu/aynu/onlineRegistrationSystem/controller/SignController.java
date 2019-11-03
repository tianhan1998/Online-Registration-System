package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.SignService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
     *
     * @param memId 学号
     * @param memName 姓名
     * @param memPassowrd 密码
     * @param memEmail 安全邮箱
     * @return json{code:200,meg:xxx,object:[]}
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public JSONObject signUp(String memId, String memName, String memPassowrd, String memEmail, String memSex)  {

        memInfo memInfo = new memInfo(Integer.valueOf(memId),memName,memEmail,"M",memPassowrd);

        if(service.singUp(memInfo)){
            String obj = JSONObject.toJSONString(memInfo);
            json.put("code",200);
            json.put("meg","注册成功");
            json.put("object",obj);
        }else{
            json.put("success","404");
            json.put("meg","用户已存在！");
        }

        return json;
    }

    /***
     *
     * @param memId 检查id重复
     * @return json{code:200,meg:"此学号可以注册"}
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.GET)
    public JSONObject checkId(Integer memId){
        return json;
    }

    /***
     *
     * @param id 前端学号
     * @param password 密码
     * @return json{code:200,meg:登陆成功,object:登录用户对象}
     */
    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public JSONObject signIn(String id,String password){
        List<memInfo> list=null;
        list=service.signIn(Integer.valueOf(id),password);
        System.out.println(list);
        if(list.size()!=0){
            String jsonstring=JSONArray.toJSONString(list.get(0));
            json.put("object",jsonstring);
            json.put("code","200");
            json.put("meg","登陆成功");
        }else{
            json.put("success","404");
            json.put("meg","学号不存在或密码错误");
        }
        return json;
    }


}
