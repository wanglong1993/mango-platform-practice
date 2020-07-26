package cn.siques.mangocore.utils;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;


public class SecurityUtils {

/**
 * 获取登陆令牌进行认证
 */

public  static  void checkAuthentication(HttpServletRequest request, HttpServletResponse response){




    // 获取令牌并获得登陆信息
    Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);



    // 如果不是网关调用，清空所有权限
   if(request.getHeader("X-Request-Id")==null||!request.getHeader("X-Request-Id").equals("heshenghao")){
       System.out.println(authentication);
       authentication=null;
   };



    // 将登陆信息设置到上下文
    // 所有接口都会调用这个过滤器，所以没有信息的时候就为空
    SecurityContextHolder.getContext().setAuthentication(authentication);
}


    /**
     * 从redis中获取登陆令牌进行认证
     */
//    public  static  void checkAuthenticationFromRedis(HttpServletRequest request, HttpServletResponse response){
//       // 模拟从网关获得用户名
//
//
//        System.out.println(token);
//        // 获取令牌并获得登陆信息
//        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request,token);
//        // 将登陆信息设置到上下文
//        // 所有接口都会调用这个过滤器，所以没有信息的时候就为空
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }



    /**
     * 获取当前登录信息
     * @return
     */
    public static Authentication getAuthentication(){
    if(SecurityContextHolder.getContext()==null){
        return null;
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println(authentication);
    return  authentication;
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            System.out.println(principal);
            if(principal != null && principal instanceof String) {
                username = principal.toString();
            }
        }
        return username;
    }



    /**
     * 获取用户名
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }




    /**
     * 系统登陆认证
     * @param request
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticationToken login(HttpServletRequest request,
                                               String username, String password, AuthenticationManager authenticationManager) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();



        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password,authorities);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // 执行登陆过程
        Authentication authenticate = authenticationManager.authenticate(token);

//         认证成功
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 生成令牌并返回
        token.setToken(JwtTokenUtils.generateToken(authenticate));
        token.eraseCredentials();

        return  token;
    }
}
