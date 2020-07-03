package cn.siques.mangogateway;

import cn.siques.mango.annotation.MyPreAuthorize;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@SpringBootTest(classes= MangoGatewayApplicationTests.class)
class MangoGatewayApplicationTests {

    @Test
    void contextLoads() throws ClassNotFoundException {
        Class c = Class.forName("cn.siques.mango.controller.SysDictController");
        Method[] dosome = c.getMethods();
        for (Method m:dosome
        ) {
         if(  m.isAnnotationPresent(MyPreAuthorize.class)){
             MyPreAuthorize annotation = m.getAnnotation(MyPreAuthorize.class);
             String value = annotation.value();
             System.out.println(value);

         }
        }
    }

}
