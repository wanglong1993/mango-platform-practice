package cn.central.oauth.config;




import cn.central.oauth.entity.SysUser;
import cn.central.oauth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * 数据库操作
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       SysUser user =  sysUserService.findUserByName(username);

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

