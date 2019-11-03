package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.SignService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/3 0003
 */
@RestController
public class SignController {
    @Autowired
    SignService service;
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public JSONObject signUp(String id,String username)  {
        JSONObject json=new JSONObject();
        return json;
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.GET)
    public JSONObject checkId(Integer id){
        JSONObject json=new JSONObject();
        return json;
    }

    @RequestMapping(value = "/signIn",method = RequestMethod.POST)
    public String signIn(String id,String password){
        List<memInfo> list=null;
        list=service.findUser(Integer.valueOf(id));
        System.out.println(list);
        return JSONArray.toJSONString(list);
    }


}
