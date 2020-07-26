package cn.siques.mango.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@ComponentScan("cn.siques.demo_project")
@EnableAspectJAutoProxy // 开启spring对切面的支持
public class AnnotationConfig {
}
