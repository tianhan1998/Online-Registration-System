package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.mapper.memInfoMapper;
import cn.edu.aynu.onlineRegistrationSystem.mapper.teamInfoMapper;
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
}

