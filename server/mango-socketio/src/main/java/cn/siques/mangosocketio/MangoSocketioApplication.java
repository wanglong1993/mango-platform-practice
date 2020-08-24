package cn.siques.mangosocketio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MangoSocketioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoSocketioApplication.class, args);
    }

}
