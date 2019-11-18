package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.service.ManageSystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageSystemTest {
    @Autowired
    ManageSystemService service;
    @Test
    public void testGetJoinedMemIdsByMatchId(){
//        List<Integer> ids;
//        ids=service.getJoinedMemIdsByMatchId(1);
//        System.out.println(ids);
    }

//    yyyy-MM-dd HH:mm:ss

    @Test
    public void testInsertMatch(){
        for(int i=0;i<300;i++)
        try {
            int rel=service.addMatch("这是一个测试比赛","2019-11-12 13:13:13","2020-12-12 13:13:13","0","123");
            System.out.println(rel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
