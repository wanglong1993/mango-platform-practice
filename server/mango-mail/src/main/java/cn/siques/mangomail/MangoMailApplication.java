package cn.siques.mangomail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class MangoMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoMailApplication.class, args);

    }
}
