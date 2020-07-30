package cn.siques.mangogateway;

import cn.siques.mango.annotation.SaveDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest(classes= MangoGatewayApplicationTests.class)
class MangoGatewayApplicationTests {

    @Test
    void contextLoads() throws ClassNotFoundException {
        Class c = Class.forName("cn.siques.mango.service.impl.SysDeptServiceImpl");
        Method[] dosome = c.getMethods();
        for (Method m:dosome
        ) {
         if(  m.isAnnotationPresent(SaveDate.class)){
             SaveDate annotation = m.getAnnotation(SaveDate.class);
             
         }
        }
    }


}
