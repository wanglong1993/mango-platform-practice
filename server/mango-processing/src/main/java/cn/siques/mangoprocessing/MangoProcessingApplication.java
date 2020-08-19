package cn.siques.mangoprocessing;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        exclude = {
                SecurityAutoConfiguration.class
        }
)
@EnableDiscoveryClient
@EnableSwagger2Doc
public class MangoProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoProcessingApplication.class, args);
    }

}
