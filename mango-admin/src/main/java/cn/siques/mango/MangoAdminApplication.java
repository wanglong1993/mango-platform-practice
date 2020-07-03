package cn.siques.mango;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@RefreshScope
@MapperScan("cn.siques.mango.dao")
public class MangoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoAdminApplication.class, args);
    }

}
