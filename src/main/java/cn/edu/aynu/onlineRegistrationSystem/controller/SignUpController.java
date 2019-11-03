package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.User;
import cn.edu.aynu.onlineRegistrationSystem.service.SignUpService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    public SignUpService service;
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public JSONObject signUp(Integer id,String username,String password,String sex,String email){
        JSONObject json=new JSONObject();
        User user=new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setEmail(email);
        if(service.signUp(user)){
            json.put("success","true");
        }else{
            json.put("success","false");
            json.put("Error","奥术大师多");
        }
        return json;
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.GET)
    public JSONObject signUp(Integer id){
        JSONObject json=new JSONObject();
        User user=service.checkId(id);
        if(user!=null){
            json.put("success","false");
            json.put("Error","此学号已被占用");
        }else{
            json.put("success","true");
            json.put("Message","此学号可以使用");
        }
        return json;
    }

}
