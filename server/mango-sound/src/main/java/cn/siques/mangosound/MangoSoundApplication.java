package cn.siques.mangosound;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
@EnableCaching
@MapperScan("cn.siques.mangosound.mapper")
public class MangoSoundApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoSoundApplication.class, args);
    }

}
