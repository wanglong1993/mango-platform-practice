package cn.siques.mangooauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.siques.mangooauth.dao")
@EnableResourceServer
public class MangoOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoOauthApplication.class, args);
    }

}
