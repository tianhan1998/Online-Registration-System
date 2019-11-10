package cn.edu.aynu.onlineRegistrationSystem.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * 获取第三级页面更详细的信息
 */
@RestController
public class InfoController {

    /**
     * 根据传入的学号返回详细信息
     * @param memId 学号
     * @return 返回JSON详细信息，如果有成绩就返回成绩，没有成绩成绩一栏返回null
     */
    public String getMemInfo(Integer memId){

        return null;
    }

    /**
     * 根据传入的团队账号返回团队的所有信息+成绩和所有成员的基本信息（学号，姓名，个人成绩）
     * 成绩没有，则成绩哪一栏返回null
     * @param id
     * @return 返回JSON信息，如果有成绩就返回成绩，没有成绩成绩一栏返回null
     */
    public String getTeamInfoAndmemInfo(Integer id){

        return null;
    }

}
