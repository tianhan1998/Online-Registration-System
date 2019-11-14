package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 这个Controller返回modelAndView类型
 */
@Controller
public class IndexControllerV2 {
    @Autowired
    IndexService service;
    /**
     * V2版本
     * 获取当前已经登录账号报名的所有比赛信息
     * @param pn 页码
     * @param length 每页显示记录数
     * @return 已经报名的比赛信息
     */
    //TODO 未实现
    @GetMapping(value = "/matchListV2",produces = "application/json;charset=utf-8")
    public ModelAndView getMatchListById(@RequestParam(defaultValue = "0") Integer pn,@RequestParam(defaultValue = "5") Integer length, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session =request.getSession();
        Integer id= (Integer) session.getAttribute("mem_id");
        List<matchInfo> matches;

        ModelAndView modelAndView=new ModelAndView();
//        System.out.println("我登陆了但是失败了"+id);
        try {
            if ("mem".equals(session.getAttribute("type"))) {
                matches = service.getMatchListByMemId(id);
                modelAndView.setViewName("mem/mem_index");
            } else {
                matches = service.getMatchListByTeamId(id);
                modelAndView.setViewName("team/team_index");
            }
            modelAndView.addObject("code","200");
            modelAndView.addObject("data",matches);
            modelAndView.addObject("msg","查询成功");
        }catch (Exception e) {
            modelAndView.addObject("code","500");
            modelAndView.addObject("msg",e.getMessage());
        }

        return modelAndView;
    }
}
