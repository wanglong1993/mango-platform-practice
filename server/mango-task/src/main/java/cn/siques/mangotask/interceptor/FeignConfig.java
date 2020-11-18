package cn.siques.mangotask.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.siques.utils.JwtUserDetails;
import cn.siques.utils.RedisUtils;
import cn.siques.utils.SecurityUtils;
import cn.siques.mangotask.config.Singleton;
import cn.siques.mangotask.service.JobService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig implements RequestInterceptor {



    @Autowired
    JobService jobService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes attributes  = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//       String token =null;
//       if  (ObjectUtil.isNull(attributes)){
//           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//           UserDetails principal = (UserDetails) SecurityUtils.getAuthentication().getDetails();
//            token = redisUtils.getValue(principal.getUsername());
//
//           requestTemplate.header("Authorization",token);
//       }else{
//           HttpServletRequest request = attributes.getRequest();

        Object admin = Singleton.getInstance().get("admin");
        requestTemplate.header("Authorization", Singleton.getInstance().get("admin").toString());
//       }


    }
}
