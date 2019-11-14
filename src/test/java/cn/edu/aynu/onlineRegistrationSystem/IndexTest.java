package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.controller.IndexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexTest {
    @Autowired
    IndexController controller;
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
}
