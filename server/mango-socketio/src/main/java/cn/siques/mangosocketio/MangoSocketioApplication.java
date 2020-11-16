package cn.siques.mangosocketio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class})
public class MangoSocketioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoSocketioApplication.class, args);
    }

}
