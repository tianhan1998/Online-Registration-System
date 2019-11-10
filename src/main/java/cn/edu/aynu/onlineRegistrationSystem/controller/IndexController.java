package cn.edu.aynu.onlineRegistrationSystem.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * 第二级页面信息
 */
@RestController
public class IndexController {
    /**
     * 获取所有比赛列表，包括个人比赛和团队比赛
     * @return 返回所有比赛信息的JSON
     */
    public String getMatchList(){

        return null;
    }

    /**
     * 根据传入的id号获取该id已经报名的所有比赛信息
     * @param id 学号或者团队号id
     * @param pn 页码
     * @param length 每页显示记录数
     * @return 已经报名的比赛信息
     */
    public String getMatchListById(Integer id,Integer pn,Integer length){

        return null;
    }

    /**
     * 根据传入的学号和比赛的id进行比赛报名（如果个人报名账号类型和比赛要求类型不匹配就失败,或者比赛有邀请码且不一致，也加入失败）
     * @param memId 学号
     * @param matchId 待报名比赛的Id号
     * @param type 0是个人账号，1团队账号
     * @param matchPassword 比赛邀请码，如果数据库没有比赛邀请码则不需填写
     * @param pn 页码
     * @param length 每页显示记录数
     * @return 成功返回成功想JSON信息，失败返回失败的JSON信息
     */
    public String memEnrollByid(Integer memId,Integer matchId,Integer type,String matchPassword,Integer pn,Integer length){

        return null;
    }

    /**
     * 根据团队账号id获取该团队的所有成员信息
     * @param matchId 团队账号id
     * @return 获取id为${id}这个团队里面所有的成员信息打包返回JSON
     */
    public String getTeamMemInfoByid(Integer matchId){

        return null;
    }

    /**
     * 根据传入团队账号id和个人账号id让个人账号加入这个团队账号
     * 如果团队人数到达上限则加入失败，如果个人账号不存在则加入失败 如果这个人已经加入这个团队，则加入失败
     * @param matchId 团队账号id
     * @param memId 个人账号id
     * @return 返回成功或者失败的JSON信息
     */
    public String joinTeamByid(Integer matchId,Integer memId){

        return null;
    }

}
