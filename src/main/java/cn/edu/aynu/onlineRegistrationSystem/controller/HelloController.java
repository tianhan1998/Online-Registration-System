package cn.edu.aynu.onlineRegistrationSystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/Hello")
    public String hello(){
        return "Hello";
    }
}
