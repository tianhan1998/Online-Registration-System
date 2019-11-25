package cn.edu.aynu.onlineRegistrationSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("cn.edu.aynu.onlineRegistrationSystem.mapper")
@SpringBootApplication
public class OnlineRegistrationSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(OnlineRegistrationSystemApplication.class, args);
    }

}
