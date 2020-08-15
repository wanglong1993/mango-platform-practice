package cn.siques.mangotask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"cn.siques.mangotask.mapper"})
@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)
public class MangoTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoTaskApplication.class, args);
    }

}
