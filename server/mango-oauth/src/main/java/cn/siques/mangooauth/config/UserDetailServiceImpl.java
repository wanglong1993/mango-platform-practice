package cn.siques.mangooauth.config;



import cn.siques.utils.GrantedAuthorityImpl;
import cn.siques.utils.JwtUserDetails;
import cn.siques.mangooauth.entity.SysUser;
import cn.siques.mangooauth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 数据库操作
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       SysUser  user =  sysUserService.findUserByName(username);

        HashSet<SimpleGrantedAuthority> roles = new HashSet<>();
         user.getMenus().forEach(sysMenu -> {
             String perm = sysMenu.getPerms();
            if (perm!=null && !perm.equals("")){
                System.out.println(perm);
                roles.add(new SimpleGrantedAuthority(perm));
            }
        });

//        System.out.println(roles);
        // 把属性放进userdetails
        return new User(user.getName(),user.getPassword(),roles);
        }
}

