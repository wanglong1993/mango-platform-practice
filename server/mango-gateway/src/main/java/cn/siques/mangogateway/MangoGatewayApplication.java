package cn.siques.mangogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MangoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoGatewayApplication.class, args);
    }

}
