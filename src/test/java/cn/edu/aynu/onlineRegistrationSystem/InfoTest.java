package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.scoreInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * wys的测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InfoTest {

    @Autowired
    private InfoService infoService;

    @Test
    public void testMemInfo() {

    }

}
