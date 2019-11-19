package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.*;
import cn.edu.aynu.onlineRegistrationSystem.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianhan
 * @date 2019/11/11
 */
@Service

public class IndexService {
    @Autowired
    matchInfoMapper MatchInfoMapper;
    @Autowired
    memMatchMapper memMatchMapper;
    @Autowired
    memInfoMapper memInfoMapper;
    @Autowired
    teamMatchMapper teamMatchMapper;
    @Autowired
    teamInfoMapper teamInfoMapper;
    @Autowired
    InviteInfoMapper inviteInfoMapper;
    @Autowired
    MessageMapper messageMapper;

    /**
     * 返回不带密码的个人信息
     * @param id
     * @return
     */
    public memInfo getMemInfosWithOutPasswordById(Integer id){
        return memInfoMapper.getMemInfosWithOutPasswordById(id);
    }

    /**
     * 通过来去两个id判断是否存在邀请
     * @param from_id
     * @param to_id
     * @return
     */
    public int checkInviteExistByFromAndTo(Integer from_id,Integer to_id){
        return inviteInfoMapper.checkExistByFromAndTo(from_id, to_id);
    }

    /**
     * 根据id检查是否存在邀请
     * @param id
     * @return
     */
    public InviteInfo checkInviteExistById(Integer id){
        return inviteInfoMapper.checkExistById(id);
    }

    /**
     * 根据邀请id删除数据库
     * @param id
     * @return
     */
    public int deleteInviteById(Integer id){
        return inviteInfoMapper.deleteInviteById(id);
    }

    /**
     * 发送邀请消息
     * @return
     */
    public int insertInvite(InviteInfo invite){
        return inviteInfoMapper.insertInviteInfo(invite);
    }

    /**
     * 个人加入团队
     * @param team 队伍实体
     * @param memId 个人学号
     * @return
     */
    public int joinTeamByid(teamInfo team,Integer memId){
        return teamInfoMapper.joinTeam(team, memId);
    }

    /**
     * 批量学号取得mems实体类
     * @param ids list存储的学号组
     * @return 返回学生实体类数组
     */
    public List<memInfo> getMemInfoByIds(List<Integer>ids){
        return memInfoMapper.getMemInfoByIds(ids);
    }

    /**
     * 通过teamid获取学号数组，不是mem实体类
     * @param teamid 队伍id
     * @return 返回学号的集合
     */
    public List<Integer> getMemidsByTeamId(Integer teamid) throws Exception{
        teamInfo team=teamInfoMapper.selectByPrimaryKey(teamid);
        if(team!=null) {
            List<Integer> lists = new ArrayList<>();
            if (team.getMemId1() != null) {
                lists.add(team.getMemId1());
            }
            if (team.getMemId2() != null) {
                lists.add(team.getMemId2());
            }
            if (team.getMemId3() != null) {
                lists.add(team.getMemId3());
            }
            return lists;
        }else{
            throw new Exception("队伍不存在");
        }
    }

    /**
     * 通过队伍id获取实体类
     * @param id
     * @return
     */
    public teamInfo getTeamInfoById(Integer id){
        return teamInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 检查团队是否参加过比赛
     * @param team_id  队伍id
     * @param match_id 比赛id
     * @return
     */
    public int checkExistInTeamMatch(Integer team_id,Integer match_id){
        return teamMatchMapper.checkExistInTeamMatch(team_id, match_id);
    }

    /**
     * 查找个人是否报名过比赛
     * @param mem_id 学号
     * @param match_id 比赛id
     * @return
     */
    public int checkExistInMemMatch(Integer mem_id, Integer match_id){
        return memMatchMapper.checkExistInMemMatch(mem_id, match_id);
    }

    /**
     * 插入一条队伍报名信息
     * @param teamMatch
     * @return
     */
    public int insertTeamMatch(teamMatch teamMatch){
        return teamMatchMapper.insertSelective(teamMatch);
    }

    /***
     * 插入一条个人报名信息
     * @param memMatch 实体类
     * @return
     */
    public int insertMemMatch(memMatch memMatch){
        return memMatchMapper.insertSelective(memMatch);
    }

    /**
     * 根据学号返回mem对象
     * @param id 学号
     * @return
     */
    public memInfo getMemInfoById(Integer id){
        return memInfoMapper.selectByPrimaryKey(id);
    }

    /***
     * 根据ID获取比赛信息
     * @param id 比赛id
     * @return
     */
    public matchInfo getMatchInfoById(Integer id){
        return MatchInfoMapper.selectByPrimaryKey(id);
    }

    /***
     * 获取所有比赛列表
     * @return
     */
    public List<matchInfo> getMatchList(){
        return MatchInfoMapper.getMatchList();
    }

    /***
     * 通过个人id获取所有个人账号参加的比赛列表
     * @param id 个人id
     * @return
     */
    public List<matchInfo> getMatchListByMemId(Integer id){
        return MatchInfoMapper.getMatchListByMemId(id);
    }

    /***
     * 通过队伍id获取队伍账号参加的比赛列表
     * @param id 队伍id
     * @return
     */
    public List<matchInfo> getMatchListByTeamId(Integer id){
        return MatchInfoMapper.getMatchListByTeamId(id);
    }

    /**
     * 将invite和message链接起来
     * @param invite
     * @param messageId
     * @return
     */
    public int updateInviteMessageId(InviteInfo invite,Integer messageId){
        return inviteInfoMapper.updateMessageId(invite, messageId);
    }

    /**
     * 返回所有被邀请的Message
     * @param to_id
     * @return
     */
    public List<MessageInfo> selectListBeInvited(Integer to_id) throws Exception {
        List<Integer> list=inviteInfoMapper.selectMessageIdsBeInvited(to_id);
        List<MessageInfo> messageInfos;
        if(list!=null){
            messageInfos=messageMapper.selectListMessageByIds(list);
            return messageInfos;
        }else{
            throw new Exception("没有任何被邀请信息");
        }
    }
}
