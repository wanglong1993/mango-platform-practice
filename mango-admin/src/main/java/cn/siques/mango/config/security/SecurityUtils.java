package cn.siques.mango.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtils {
/**
 * 获取登陆令牌进行认证
 */
public  static  void checkAuthentication(HttpServletRequest request){
    // 获取令牌并获得登陆信息
    Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
    // 将登陆信息设置到上下文
    SecurityContextHolder.getContext().setAuthentication(authentication);
}

    /**
     * 获取当前登录信息
     * @return
     */
    public static Authentication getAuthentication(){
    if(SecurityContextHolder.getContext()==null){
        return null;
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
            if(principal != null ) {
                username =  principal.toString();
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
            if(principal != null ) {
                username =  principal.toString();
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

        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登陆过程
        Authentication authenticate = authenticationManager.authenticate(token);
        // 认证成功
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // 生成令牌并返回
        token.setToken(JwtTokenUtils.generateToken(authenticate));
        return  token;
    }
}
