package cn.siques.mangogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class MangoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoGatewayApplication.class, args);
    }

}
