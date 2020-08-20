package cn.siques.mango;


import cn.siques.mango.config.DbProperty;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2Doc
@RefreshScope
@EnableCaching
//@EnableAspectJAutoProxy
@MapperScan({"cn.siques.mangocore.dao","cn.siques.mango.mapper"})
public class MangoAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoAdminApplication.class, args);
    }

}
