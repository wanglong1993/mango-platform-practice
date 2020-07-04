package cn.siques.mangomenu;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@EnableCaching
@MapperScan("cn.siques.mangocore.dao")
public class MangoMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoMenuApplication.class, args);
    }

}
