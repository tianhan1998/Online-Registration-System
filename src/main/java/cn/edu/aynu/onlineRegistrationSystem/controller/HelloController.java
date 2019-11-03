package cn.edu.aynu.onlineRegistrationSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @RequestMapping("/Hello")
    public String hello(){
        return "/123";
    }
}
