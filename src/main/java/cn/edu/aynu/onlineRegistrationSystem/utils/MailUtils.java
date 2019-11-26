package cn.edu.aynu.onlineRegistrationSystem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author wys
 * @date 2019/11/24
 * 邮箱工具类
 */
@Component
public class MailUtils {

    /**
     * 邮件发送方
     */
    private final String FORM = "714133840@qq.com";

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * @param to 邮件接收方
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return
     */
    public boolean sendMail(String to,String subject,String content) throws Exception {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
        mimeMessageHelper.setFrom(FORM);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);

        javaMailSender.send(mimeMessage);

        return true;
    }

}
