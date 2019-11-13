package cn.edu.aynu.onlineRegistrationSystem;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("cn.edu.aynu.onlineRegistrationSystem.mapper")
@SpringBootApplication
public class OnlineRegistrationSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(OnlineRegistrationSystemApplication.class, args);
    }

}
