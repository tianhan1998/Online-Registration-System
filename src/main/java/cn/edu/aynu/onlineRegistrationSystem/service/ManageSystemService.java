package cn.edu.aynu.onlineRegistrationSystem.service;

import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/13 0013
 */
@Service
public class ManageSystemService {
    @Autowired
    memInfoMapper memInfoMapper;
    @Autowired
    teamInfoMapper teamInfoMapper;
    @Autowired
    matchInfoMapper matchInfoMapper;
    @Autowired
    memMatchMapper memMatchMapper;
    @Autowired
    teamMatchMapper teamMatchMapper;
    /**
     * 获取所有meminfo
     * @return
     */
    public List<memInfo> getMemInfoLists(){
        return memInfoMapper.getMemInfoLists();
    }

    /**
     * 修改用户，除了学号
     * @param user
     * @return
     */
    public int updateMem(memInfo user){
        return memInfoMapper.updateByPrimaryKey(user);
    }

    /**
     * 通过学号删除用户
     * @param id
     * @return
     */
    public int deleteMem(Integer id){
        return memInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 取得队伍列表
     * @return
     */
    public List<teamInfo>getTeamList(){
        return teamInfoMapper.getTeamList();
    }

    /**
     * 更新队伍信息
     * @param team
     * @return
     */
    public int updateTeam(teamInfo team){
        return teamInfoMapper.updateByPrimaryKeySelective(team);
    }

    /**
     * 删除队伍
     * @param id
     * @return
     */
    public int deleteTeam(Integer id){
        return teamInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据比赛id获取比赛实体类
     * @param id
     * @return
     */
    public matchInfo getMatch(Integer id){
        return matchInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过传入比赛id获取所有参加比赛的个人学号
     * @param id 比赛id
     * @return
     */
    public List<Integer> getJoinedMemIdsByMatchId(Integer id){
        return memMatchMapper.getJoinedMemByMatchId(id);
    }

    /**
     * 通过传入比赛id获取所有参加比赛的团队id
     * @param id
     * @return
     */
    public List<Integer> getJoinedTeamIdsByMatchId(Integer id){
        return teamMatchMapper.getJoinedTeamByMatchId(id);
    }

    /**
     * 通过队伍id获取队伍实体类
     * @param id
     * @return
     */
    public teamInfo getTeamByTeamId(Integer id){
        return teamInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 通过学号组传回所有用户实体
     * @param ids
     * @return
     */
    public List<memInfo> getMemListsByIds(List<Integer>ids){
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
}
