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

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String jumpRegister() {
        return "register";
    }
    @RequestMapping(value = "/mem_tables",method = RequestMethod.GET)
    public String jumpTables() {
        return "mem_index";
    }

    @RequestMapping(value = "/mem_tables_all",method = RequestMethod.GET)
    public String jumpTablesAll() {
        return "mem_index_all_list";
    }
}
