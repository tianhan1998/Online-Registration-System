package cn.edu.aynu.onlineRegistrationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/3 0003
 */
@Controller
public class JumpController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String jumpHello(){
        return "login";
    }
}
