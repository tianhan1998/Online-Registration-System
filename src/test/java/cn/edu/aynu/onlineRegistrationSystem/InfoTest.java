package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
    public void updateMemInfo() {
        memInfo memInfo=new memInfo();
        memInfo.setMemName("张珊");
        infoService.setMemInfo(174804155,memInfo);
    }

    @Test
    public void updateTeamInfo() {
        teamInfo teamInfo=new teamInfo();
        teamInfo.setTeamName("哈哈哈哈");
        infoService.setTeamInfo(3,teamInfo);
    }

}
