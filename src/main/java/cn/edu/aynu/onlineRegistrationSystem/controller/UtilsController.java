package cn.edu.aynu.onlineRegistrationSystem.controller;

import cn.edu.aynu.onlineRegistrationSystem.entity.RSABean;
import cn.edu.aynu.onlineRegistrationSystem.utils.RSA;
import cn.edu.aynu.onlineRegistrationSystem.utils.VerifyCode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
public class UtilsController {

    /**
     * 获取公钥
     * @return
     */
    @GetMapping(value = "/getPublicKey")
    public JSONObject getPublicKey(HttpServletRequest request){
        RSA rsa=null;
        JSONObject json=new JSONObject();
        HttpSession session=request.getSession();
        try {
            rsa=RSA.getInstance();
            RSABean rsaBean=rsa.getRandomKey();
            session.setAttribute("privateKey",rsaBean.getPrivateKey());
            json.put("data",rsaBean.getPublicKey());
        } catch (Exception e) {
            json.put("code","500");
            json.put("msg",e.getMessage());
        }
        return json;
    }

    /**
     * 获取验证码
     */
    @GetMapping(value = "/getVeifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        VerifyCode code=new VerifyCode();
        code.output(code.getImage(),response.getOutputStream());
        session.setAttribute("code",code.getText());
    }
}
