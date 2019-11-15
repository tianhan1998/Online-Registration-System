package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.service.ManageSystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
}
