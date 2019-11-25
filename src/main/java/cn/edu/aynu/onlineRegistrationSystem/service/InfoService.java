package cn.edu.aynu.onlineRegistrationSystem.service;


import cn.edu.aynu.onlineRegistrationSystem.entity.*;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.scoreInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamMatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @author wys
 * @date 2019/11/11
 */
@Service
public class InfoService {

    @Autowired
    scoreInfoMapper scoreInfoMapper;
    @Autowired
    memInfoMapper memInfoMapper;
    @Autowired
    teamInfoMapper teamInfoMapper;
    @Autowired
    teamMatchMapper teamMatchMapper;


    /**
     * 根据比赛id和学号查询比赛成绩
     * @param matchId 比赛id
     * @param memId 学号
     * @return 返回的是查询的成绩信息
     */
    private scoreInfo getScoreInfo(Integer matchId, Integer memId) {

        //通过条件查询
        scoreInfoExample scoreInfoExample = new scoreInfoExample();
        //按 id 升序排序
        scoreInfoExample.setOrderByClause("id asc");
        scoreInfoExample.setDistinct(true);
        //构造自定义查询条件
        scoreInfoExample.Criteria criteria = scoreInfoExample.createCriteria();
        criteria.andMemIdEqualTo(memId);
        criteria.andMatchIdEqualTo(matchId);

        List<scoreInfo> scoreInfoList = scoreInfoMapper.selectByExample(scoreInfoExample);

        return scoreInfoList.get(0);
    }

    /**
     * 根据传进来的学号加类型，返回不同的值
     * 如果是个人账号返回所有的信息加成绩
     * @param memId 学号
     * @param matchId 比赛的id
     */
    public List<Object> getMemScore(Integer memId, Integer matchId) {
        List<Object> list = new ArrayList<>();
        memInfo memInfo = memInfoMapper.selectByPrimaryKey(memId);
        list.add(memInfo);
        scoreInfo scoreinfo= getScoreInfo(matchId,memId);
        list.add(scoreinfo);
        return list;
    }

    /**
     * 根据团队id和比赛id查询所有信息
     * @param teamId 团队id
     * @param matchId 比赛id
     * @return 返回的是一个储存所有信息的集合
     */
    public List<Object> getTeamScore(Integer teamId, Integer matchId) {
        List<Object> list = new ArrayList<>();
        List<Map<String,Object>> maps = new ArrayList<>();
        teamInfo teamInfo = teamInfoMapper.selectByPrimaryKey(teamId);
        maps.add(getMemInfoScoreMap(teamInfo.getMemId1(),matchId));
        maps.add(getMemInfoScoreMap(teamInfo.getMemId2(),matchId));
        maps.add(getMemInfoScoreMap(teamInfo.getMemId3(),matchId));
        list.add(maps);
        return list;


    }

    /**
     * 将学号姓名和成绩封装到map中
     * @param memId 个人id
     * @param matchId 比赛id
     * @return 返回的是用map封装过的个人信息
     */
    private Map<String,Object> getMemInfoScoreMap(Integer memId, Integer matchId) {
        Map<String,Object> map = new HashMap<>();
        memInfo m = memInfoMapper.selectByPrimaryKey(memId);
        scoreInfo s = getScoreInfo(matchId,m.getMemId());
        map.put("memId",m.getMemId());
        map.put("memName",m.getMemName());
        map.put("score",s.getMemScore());

        return map;
    }

    /**
     * 根据id获取个信息
     * @param id 个人id
     * @return
     */
    public memInfo getMemInfo(Integer id){//获取个人信息
        List<Integer> in=new ArrayList<Integer>();
        in.add(id);
        List<memInfo> list=memInfoMapper.getMemInfoByIds(in);
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }

    /**
     * 根据id修改这个人的信息不能修改密码
     * @param id
     * @param mem
     * @return
     */
    public Integer setMemInfo(Integer id,memInfo mem){
        mem.setMemId(id);
        mem.setMemPassword(null);
        return memInfoMapper.updateByPrimaryKeySelective(mem);
    }

    /**
     * 根据id获取团队个人信息
     * @param id
     * @return
     */
    public teamInfo getTeamInfo(Integer id){
        return teamInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键修改团队信息但是不能修改密码
     * @param id
     * @param team
     * @return
     */
    public Integer setTeamInfo(Integer id,teamInfo team){
        team.setTeamPassword(null);
        team.setTeamId(id);
        return teamInfoMapper.updateByPrimaryKeySelective(team);
    }


    /**
     * 获取当前个人账号加入团队的列表
     * @return
     */
    public List<MatchAppleInfo> getJoinTeamInfoList(Integer memId){
        List<MatchAppleInfo> teamInfoList=teamInfoMapper.getTeamInfoByMemId(memId);
        for (MatchAppleInfo matchAppleInfo : teamInfoList) {
            List<Integer> memList=new ArrayList<Integer>();
            if (matchAppleInfo.getTeamInfo()!=null){
                if(matchAppleInfo.getTeamInfo().getMemId1()!=null){
                    memList.add(matchAppleInfo.getTeamInfo().getMemId1());
                }
                if(matchAppleInfo.getTeamInfo().getMemId2()!=null){
                    memList.add(matchAppleInfo.getTeamInfo().getMemId2());
                }
                if(matchAppleInfo.getTeamInfo().getMemId3()!=null){
                    memList.add(matchAppleInfo.getTeamInfo().getMemId3());
                }
            }
            else
                matchAppleInfo.setTeamInfo(new teamInfo());

            List<memInfo> memInfoList=new ArrayList<memInfo>();
            if(memList.size()!=0)
                memInfoList=memInfoMapper.getMemInfoByIds(memList);
            matchAppleInfo.getTeamInfo().setMemList(memInfoList);
        }
        return teamInfoList;
    }

    /**
     * 修改个人账号密码
     * @param id 带修改人的id
     * @param password 修改后的密码
     * @return
     */
    public Integer setMemPassword(Integer id,String password){
        memInfo mem=new memInfo();
        mem.setMemId(id);
        mem.setMemPassword(password);
        return memInfoMapper.updateByPrimaryKeySelective(mem);
    }

    /**
     * 修改团队账号密码
     * @param id 带修改团队的id
     * @param password 修改后的密码
     * @return
     */
    public Integer setTeamPassword(Integer id,String password){
        teamInfo team=new teamInfo();
        team.setTeamId(id);
        team.setTeamPassword(password);
        return teamInfoMapper.updateByPrimaryKeySelective(team);
    }

}
