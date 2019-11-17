package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.controller.IndexController;
import cn.edu.aynu.onlineRegistrationSystem.entity.matchInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexTest {
    @Autowired
    IndexController controller;
    @Autowired
    IndexService service;

    @Test
    public void testFindMatchList(){
        controller.getMatchList();
    }
    @Test
    public void memEnrollByid(){
    }
    @Test
    public void getTeamMemInfoByid() {
        /*controller.getTeamMemInfoById(1);*/
    }
    @Test
    public void joinTeamById(){
        /*controller.joinTeamById(1,174804131);
        controller.joinTeamById(1,174804155);*/
    }
    @Test
    public void getMatchListByTeamId(){
        List<matchInfo> list=service.getMatchListByTeamId(1);
        for (matchInfo info : list) {
            System.out.println(info);
        }
    }
}
