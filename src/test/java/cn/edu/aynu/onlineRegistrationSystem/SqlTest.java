package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.entity.MatchAppleInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.memMatch;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memMatchMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamMatchMapper;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/3 0003
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlTest {
    @Autowired
    memInfoMapper map;
    @Autowired
    teamInfoMapper teamInfoMapper;
    @Autowired
    memMatchMapper memMapper;

    @Autowired
    teamMatchMapper teamMapper;

    @Autowired
    IndexService service;
    /**
     * 插入个人信息
     */
    @Test
    public void insertMemInfo(){
        map.insert(new memInfo(174804131,"tianhan","892265525@qq.com","0","123456789"));
    }

    @Test
    public void getList(){
        List<teamInfo> list=teamInfoMapper.getTeamInfoByMemId(174804155);
        for (teamInfo teamInfo : list) {
            System.out.println(teamInfo.getTeamName());
        }
    }


    @Test
    public void getAppleTeam(){
        List<MatchAppleInfo> match=service.getMatchInfoByMatchIdWithTeamInfo(6);
        for (MatchAppleInfo matchAppleInfo : match) {
            System.out.println(matchAppleInfo.getMatchTitle()+"--"+matchAppleInfo.getTeamInfo());
        }
    }

    @Test
    public void getAppleMem(){
        List<MatchAppleInfo> match=memMapper.getMemMatchInfoByMatchId(4);
        System.out.println(match.size());
        for (MatchAppleInfo matchAppleInfo : match) {
            System.out.println(matchAppleInfo.getMatchTitle()+"--"+match.size()+"**"+matchAppleInfo.getMemInfo().getMemName());
        }
    }

    @Test
    public void getAll(){
        List<memMatch> list=memMapper.getAll();
        System.out.println(list.size());
    }

}

