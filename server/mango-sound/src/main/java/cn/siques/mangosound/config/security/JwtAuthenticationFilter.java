package cn.siques.mangosound.config.security;

import cn.hutool.core.util.StrUtil;

import cn.siques.mangocommon.utils.JwtTokenUtils;


import cn.siques.mangocommon.utils.RedisUtils;
import org.apache.poi.hssf.record.pivottable.PageItemRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class JwtAuthenticationFilter  extends BasicAuthenticationFilter {


    private RedisUtils<String,String > redisUtils;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
        super(authenticationManager);
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpSession session = request.getSession();
//        String id = session.getId();
//        System.out.println(id);


        String token = JwtTokenUtils.getToken(request);
        if(StrUtil.isNotBlank(token) && !JwtTokenUtils.isTokenExpired(token)){
            String username = JwtTokenUtils.getUsernameFromToken(token);
            String tokenValue = redisUtils.getValue(username);

            // redis中非空
            if(StrUtil.isNotBlank(tokenValue)){
                // 上下文非空
                Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(tokenValue);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            // 若为空，不在验证，需重新登录
        }

        // 判断
        chain.doFilter(request,response);
    }
}
