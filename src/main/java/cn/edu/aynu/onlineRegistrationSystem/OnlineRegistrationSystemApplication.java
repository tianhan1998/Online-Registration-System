package cn.edu.aynu.onlineRegistrationSystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("cn.edu.aynu.onlineRegistrationSystem.mapper")
@EnableScheduling
@SpringBootApplication
public class OnlineRegistrationSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(OnlineRegistrationSystemApplication.class, args);
    }

}
