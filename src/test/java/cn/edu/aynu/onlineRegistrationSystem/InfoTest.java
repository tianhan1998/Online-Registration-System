package cn.edu.aynu.onlineRegistrationSystem;

import cn.edu.aynu.onlineRegistrationSystem.entity.memInfo;
import cn.edu.aynu.onlineRegistrationSystem.entity.teamInfo;
import cn.edu.aynu.onlineRegistrationSystem.service.InfoService;
import cn.edu.aynu.onlineRegistrationSystem.utils.MailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

/**
 * wys的测试类
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InfoTest {

    @Autowired
    private InfoService infoService;
    @Autowired
    private MailUtils mailUtils;

    @Test
    public void testMail() throws Exception {
        mailUtils.sendMail("2900086967@qq.com","测试","测试邮件");
    }

}
