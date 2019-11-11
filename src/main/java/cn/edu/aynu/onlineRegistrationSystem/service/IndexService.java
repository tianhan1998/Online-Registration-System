package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.*;
import cn.edu.aynu.onlineRegistrationSystem.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return teamMatchMapper.insert(teamMatch);
    }

    /***
     * 插入一条个人报名信息
     * @param memMatch 实体类
     * @return
     */
    public int insertMemMatch(memMatch memMatch){
        return memMatchMapper.insert(memMatch);
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
}
