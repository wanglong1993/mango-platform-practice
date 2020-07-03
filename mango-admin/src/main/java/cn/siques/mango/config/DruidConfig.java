package cn.siques.mango.config;

import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Log4jFilterMBean;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix="spring.datasource.druid")
    @Bean
    public DataSource durid(){
        return new DruidDataSource();
    }

   @Bean
    @ConditionalOnMissingBean
    public ServletRegistrationBean<Servlet> druidServlet(){
       ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
       // 白名单
       servletServletRegistrationBean.addInitParameter("allow","127.0.0.1");

       // 黑名单
//       servletServletRegistrationBean.addInitParameter("deny","");
       servletServletRegistrationBean.addInitParameter("loginUsername","admin");
       servletServletRegistrationBean.addInitParameter("loginPassword","admin");
        servletServletRegistrationBean.addInitParameter("resetEnable","true");
        return servletServletRegistrationBean;
   }


    /**
     * 注册filter信息
     */
    @Bean
    public FilterRegistrationBean<WebStatFilter> webStatFilter(){
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<WebStatFilter>();
        bean.setFilter(new WebStatFilter());


        Map<String,String> initParams = new HashMap<String,String>();
        //忽略过滤的形式
        initParams.put("exclusions", "*.js,*.css,/druid/*,/actuator/*");

        bean.setInitParameters(initParams);
        //设置过滤器过滤路径
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

    @Bean
    public Log4jFilter log(){
        //TODO 日志打印问题
        return  new Log4jFilter();
    }


}
