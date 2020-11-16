package cn.siques.mango.config;

//import cn.siques.mango.config.interceptor.DateMsgInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(dateMsgInterceptor());
//
//        WebMvcConfigurer.super.addInterceptors(registry);
//    }
//
//
//    @Bean
//    public DateMsgInterceptor dateMsgInterceptor(){
//        return  new DateMsgInterceptor();
//    }
//
//}
