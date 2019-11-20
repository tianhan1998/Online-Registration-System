package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.service.JumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/team_tables_all",method = RequestMethod.GET)
    public String jumpTablesTeamAll() {
        return "team/team_index_all_list";
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

    @RequestMapping(value = "/agree",method = RequestMethod.GET)
    public String jumpAgree() {
        return "mem/agree_list";
    }

    @RequestMapping(value = "sys/login",method = RequestMethod.GET)
    public String jumpLogin() {
        return "system/login";
    }

    @RequestMapping(value = "sys/getTeam",method = RequestMethod.GET)
    public String jumpGetTeam() {
        return "system/sys_index";
    }

    @RequestMapping(value = "sys/getMem",method = RequestMethod.GET)
    public String jumpGetMatch() {
        return "system/mem_list";
    }

    @RequestMapping(value = "sys/putMatch",method = RequestMethod.GET)
    public String jumpPutMatch() {
        return "system/new_match";
    }

    @RequestMapping(value = "sys/List",method = RequestMethod.GET)
    public String jumpMatchList() {
        return "system/list";
    }

    @RequestMapping(value = "/message",method = RequestMethod.GET)
    public String jumpMessage() {
        return "mem/agree_list";
    }

    @RequestMapping(value = "/search_join",method = RequestMethod.GET)
    public String jumpJoinTeam() {
        return "team/search_join";
    }

    @RequestMapping(value = "/mem_info",method = RequestMethod.GET)
    public String jumpMemInfo() {
        return "mem/mem_info";
    }
}
