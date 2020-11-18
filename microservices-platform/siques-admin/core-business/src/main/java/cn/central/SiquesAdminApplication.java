package cn.central;


import com.spring4all.swagger.EnableSwagger2Doc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@RefreshScope
@EnableCaching
//@EnableAspectJAutoProxy
@MapperScan("cn.central.dao")
public class SiquesAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiquesAdminApplication.class, args);
    }

}
