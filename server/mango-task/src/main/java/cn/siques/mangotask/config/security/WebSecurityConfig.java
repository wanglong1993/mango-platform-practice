package cn.siques.mangotask.config.security;//package cn.siques.mangosocketio.config.security;



import cn.siques.utils.RedisUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // 开启spring security
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解@PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RedisTemplate<String,String > redisTemplate;


    @Bean
    public RedisUtils redisUtils() {

        return new RedisUtils(redisTemplate);  //该对象为Jar包对象

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用csrf
        http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()

                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                //web jars
                .antMatchers("/webjars/**").permitAll()
                // sql监控druid
                .antMatchers("/druid/**").permitAll()
                // 首页和登陆页面
//        .antMatchers("/").permitAll().antMatchers("/login").permitAll()
                //swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers("/api/sys/v1/pub/**").permitAll()
                .antMatchers("/api/sys/v1/pri/**").permitAll()
                // 服务监控
                .antMatchers("/actuator/**").permitAll()

                // 其他所有请求需要身份认证
                // 取消session管理？？
                .anyRequest().authenticated().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) ;

        // token 验证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager(),redisUtils()), UsernamePasswordAuthenticationFilter.class);

    }



    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
