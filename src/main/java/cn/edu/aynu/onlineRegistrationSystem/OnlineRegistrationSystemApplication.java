package cn.edu.aynu.onlineRegistrationSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OnlineRegistrationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineRegistrationSystemApplication.class, args);
    }

}
