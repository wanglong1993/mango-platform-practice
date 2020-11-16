package cn.siques.mangocommon.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken implements Serializable {
    private static  final long serialVersionUID =1l;

    private String token;

    private List<GrantedAuthorityImpl> permissions;


    public JwtAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtAuthenticationToken(Object principal,Object credentials ,String token){
        super(principal,credentials);
        this.token =token;
    }

    public JwtAuthenticationToken(Object principal,Object credentials , Collection<? extends GrantedAuthority> authorities){
        super(principal, credentials, authorities);
    }

    public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,String token) {
        super(principal, credentials, authorities);
//        this.principal =principal;
        this.token =token;
    }

    public String getToken() {
        return token;
    }

    public JwtAuthenticationToken setToken(String token) {
        this.token = token;

        return this;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<GrantedAuthorityImpl> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<GrantedAuthorityImpl> permissions) {
        this.permissions = permissions;
    }
}
