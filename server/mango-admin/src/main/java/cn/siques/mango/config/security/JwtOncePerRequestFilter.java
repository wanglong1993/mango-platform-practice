package cn.siques.mango.config.security;

import cn.hutool.core.util.StrUtil;
import cn.siques.mango.service.SysUserService;
import cn.siques.mangocommon.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

@Component
public class JwtOncePerRequestFilter extends OncePerRequestFilter {
    @Autowired
     UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = JwtTokenUtils.getToken(request);

        // 每次请求都会及时更新用户权限，即使前端有按钮权限，传到后端也会报错
        if(StrUtil.isNotBlank(token)){
            try {
                Boolean tokenExpired = JwtTokenUtils.isTokenExpired(token);
                String username="anonymousUser";
                if(!tokenExpired){
                     username = JwtTokenUtils.getUsernameFromToken(token);
                }

                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);


            }catch (SecurityException e){
                ResponseUtil.renderJson(response,e.getMessage());
            }
        }
        filterChain.doFilter(request,response);


    }
}
