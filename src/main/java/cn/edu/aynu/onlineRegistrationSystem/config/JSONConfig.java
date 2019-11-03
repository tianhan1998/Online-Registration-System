package cn.edu.aynu.onlineRegistrationSystem.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2019/11/3 0003
 */
@Configuration
public class JSONConfig {

    @Bean
    public JSONObject jsonObject() {
        return new JSONObject();
    }
}
