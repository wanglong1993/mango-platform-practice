package cn.siques.mango.config.security;

import cn.hutool.core.util.StrUtil;
import cn.siques.mango.config.RedisUtils;
import cn.siques.mango.service.SysUserService;
import cn.siques.mangocommon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

//@Component
//public class JwtOncePerRequestFilter extends OncePerRequestFilter {
//    @Autowired
//     UserDetailsService userDetailService;
//
//    @Autowired
//    RedisUtils<String,String> redisUtils;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String token = JwtTokenUtils.getToken(request);
//
//        // token非空且未过期
//        if(StrUtil.isNotBlank(token) && !JwtTokenUtils.isTokenExpired(token)){
//            try {
//
//                    // 解析用户名
//                String  username = JwtTokenUtils.getUsernameFromToken(token);
//                    String tokenVal = redisUtils.getValue(username);
//                    // redis中token非空
////                    if(StrUtil.isNotBlank(tokenVal)){
////                        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(tokenVal);
////
////                        SecurityContextHolder.getContext().setAuthentication(authentication);
////                    }else{
//                        // 权限被更改过，redis中token为空，但前端token又未过期
//                        UserDetails userDetails = userDetailService.loadUserByUsername(username);
//
//                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext()
//                                .setAuthentication(authentication);
//                    }
//
//            catch (SecurityException e){
//                ResponseUtil.renderJson(response,e.getMessage());
//            }
//        }
//        filterChain.doFilter(request,response);
//
//
//    }
//}
