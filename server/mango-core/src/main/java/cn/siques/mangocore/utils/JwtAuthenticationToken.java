package cn.siques.mangocore.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken implements Serializable {
    private static  final long serialVersionUID =1l;

    private String token;

    public JwtAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtAuthenticationToken(Object principal,Object credentials ,String token){
        super(principal,credentials);
        this.token =token;
    }

    public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,String token) {
        super(principal, credentials, authorities);
        this.token =token;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
