package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.service.JumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/3 0003
 */
@Controller
public class JumpController {
    @Autowired
    JumpService service;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String jumpHello(HttpServletRequest request){
        service.clearSession(request.getSession());
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String jumpRegister() {
        return "register";
    }
    @RequestMapping(value = "/mem_tables",method = RequestMethod.GET)
    public String jumpTablesMem() {
        return "mem/mem_index";
    }

    @RequestMapping(value = "/team_tables",method = RequestMethod.GET)
    public String jumpTablesTeam() {
        return "team/team_index";
    }

    @RequestMapping(value = "/mem_tables_all",method = RequestMethod.GET)
    public String jumpTablesAll() {
        return "mem/mem_index_all_list";
    }

    @RequestMapping(value = "/join_team",method = RequestMethod.GET)
    public String jumpJoinTeamList() {
        return "mem/join_team_list";
    }

    @RequestMapping(value = "/404",method = RequestMethod.GET)
    public String jump404() {
        return "error/404";
    }

    @RequestMapping(value = "/500",method = RequestMethod.GET)
    public String jump500() {
        return "error/500";
    }
}
