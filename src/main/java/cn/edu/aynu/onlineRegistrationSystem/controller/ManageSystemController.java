package cn.edu.aynu.onlineRegistrationSystem.controller;

import com.alibaba.fastjson.JSONObject;

/**
 * 后台管理系统
 */
public class ManageSystemController {

    /**
     * 后台管理员登陆判断页面，账号密码写入配置文件中
     * @param accound 账号
     * @param password 密码
     * @return
     */
    public JSONObject loginIn(String accound,String password){

        return null;
    }

    /**
     * 获取所有个人账号所有信息
     * @param pn 页码
     * @param length 每页显示个数
     * @return
     */
    public JSONObject getMemList(Integer pn,Integer length){

        return null;
    }

    /**
     * 修改id为${memId}的用户的所有信息除了学号
     * @param memId 学号
     * @param password 修改后的密码
     * @param sex 修改后的性别
     * @param name 修改后的名字
     * @param email 修改后的email
     * @return JSON
     */
    public JSONObject modifyMemInfo(Integer memId,String password,String sex,String name,String email){

        return null;
    }

    /**
     * 删除学号为${memId}的个人账号
     * @param memId
     * @return
     */
    public JSONObject deleteMem(Integer memId){

        return null;
    }

    /**
     * 获取所有的团队信息
     * @param pn 页码
     * @param length 每页显示的个数
     * @return
     */
    public JSONObject getTeamList(Integer pn,Integer length){

        return null;
    }

    /**
     * 根据团队账号id修改除了账号和队员的所有信息
     * @param id 团队id
     * @param name 团队修改后名字
     * @param password 团队修改后密码
     * @param email 团队修改后email
     * @return
     */
    public JSONObject modefTeamInfo(Integer id,String name,String password,String email){

        return null;
    }

    /**
     * 根据id删除该团队
     * @param id 团队账号id
     * @return
     */
    public JSONObject deleteTeam(Integer id){

        return null;
    }

    /**
     * 根据传入的比赛id获取所有参赛人员基本信息列表，
     * 团队赛获取团队名和队员姓名+学号+性别
     * 个人赛获取学号+姓名+性别
     * @param id 比赛表id
     * @return
     */
    public JSONObject getMatchInfoList(Integer id){

        return null;
    }

}
