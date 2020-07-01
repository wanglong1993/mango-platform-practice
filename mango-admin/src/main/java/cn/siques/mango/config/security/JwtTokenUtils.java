package cn.siques.mango.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.awt.geom.Arc2D;
import java.io.Serializable;
import java.util.*;

public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    private static final String USERNAME = Claims.SUBJECT;
    /**
     * 创建时间
     */
    private static final String CREATED = "created";
    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";
    /**
     * 密钥
     */
    private static final String SECRET = "abcdefgh";
    /**
     * 有效期12小时
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;

    /**
     * 解析token
     * @param request
     * @return
     */
    public static Authentication getAuthenticationFromToken(HttpServletRequest request){
        Authentication authentication =null;
        // 获取请求携带的令牌
        String token = JwtTokenUtils.getToken(request);
        if(token!=null){
            // 请求令牌不能为空
            if(SecurityUtils.getAuthentication()==null){
                // 上下文中的getAuthentication为空
                Claims claims =getClaimsFromToken(token);
                // 拿到解密的用户信息
                if(claims==null){
                    return null;
                }
                String username = claims.getSubject();
                if(username==null){
                    return null;
                }
                if (isTokenExpired(token)){
                    return null;
                }
                // 权限列表
                Object authors = claims.get(AUTHORITIES);
                List<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();
                if(authors!=null && authors instanceof List){
                    for (Object object: (List)authors
                         ) {
                        authorityList.add(new GrantedAuthorityImpl((String)((Map) object).get("authority")));
                    }
                }
                authentication = new JwtAuthenticationToken(username,null,authorityList,token);
            }else {
                if(validateToken(token,SecurityUtils.getUsername())){
                    // 如果上下文中的authentication非空，且令牌合法
                    authentication = SecurityUtils.getAuthentication();
                }
            }
        }
        return  authentication;
    }


    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static Boolean isTokenExpired(String token){
       try{ Claims claimsFromToken = getClaimsFromToken(token);
           Date expiration = claimsFromToken.getExpiration();
           return expiration.before(new Date());

       }catch (Exception e){
           return  false;
       }
    }

    public  static Boolean  validateToken(String token, String username){
        String usernameFromToken = getUsernameFromToken(token);
        return (usernameFromToken.equals(username) && !isTokenExpired(token));
    }

    /**
     * 获取请求token
     */
    public static String getToken(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer ";
        if(token==null){
            token = request.getHeader("token");
        }else if(token.contains(tokenHead)){
            token =token.substring(tokenHead.length());
        }
        if("".equals(token)){
            token = null;
        }
        return token;
    }


    /**
     * 从token中解析用户名
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        }catch(Exception e){
           claims =null;
        }
        return  claims;
    }

    /**
     * 生成令牌
     * @param authenticate
     * @return
     */
    public static String generateToken(Authentication authenticate) {
        Map<String,Object> claims =  new HashMap<>(3);
        claims.put(USERNAME,SecurityUtils.getUsername(authenticate));
        claims.put(CREATED,new Date());
        claims.put(AUTHORITIES,authenticate.getAuthorities());
        return generateToken(claims);
    }

    private static String generateToken(Map<String, Object> claims) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512,SECRET).compact();
    }
}
