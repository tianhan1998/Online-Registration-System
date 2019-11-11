package cn.edu.aynu.onlineRegistrationSystem.service;


import cn.edu.aynu.onlineRegistrationSystem.entity.*;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.scoreInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamMatchMapper;
import com.sun.javafx.collections.MappingChange;
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


}
