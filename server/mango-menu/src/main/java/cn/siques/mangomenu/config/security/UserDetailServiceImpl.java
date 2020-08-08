package cn.siques.mangomenu.config.security;


import cn.siques.mangocommon.utils.GrantedAuthorityImpl;
import cn.siques.mangocommon.utils.JwtUserDetails;
import cn.siques.mangocore.entity.SysUser;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据库操作
 */
@Service("myService")
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = new SysUser();
        user.setName("admin");
        user.setPassword("123321");
        List<GrantedAuthorityImpl> collect = new ArrayList<>();
        // 把属性放进userdetails
        return new JwtUserDetails(user.getName(),user.getPassword(),user.getSalt(),collect);
    }
}


