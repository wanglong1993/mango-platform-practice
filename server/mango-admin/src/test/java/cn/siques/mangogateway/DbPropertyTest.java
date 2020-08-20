package cn.siques.mangogateway;


import cn.siques.mango.config.DbProperty;
import cn.siques.mango.controller.dto.DbConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import javax.validation.constraints.NotNull;
import java.util.List;

@SpringBootTest(classes= MangoGatewayApplicationTests.class)

@EnableConfigurationProperties(DbProperty.class)
public class DbPropertyTest {
//    @Autowired
//    DbProperty dbProperty;

   
    @Test
    void test(){
//       List<DbConfig> multiplesource = dbProperty.getMultiplesource();

    }

}
