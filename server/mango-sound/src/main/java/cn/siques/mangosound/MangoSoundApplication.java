package cn.siques.mangosound;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@RefreshScope
@MapperScan("cn.siques.mangosound.mapper")
public class MangoSoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoSoundApplication.class, args);
    }

}
