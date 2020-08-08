package cn.siques.mangosound.config.security;



import cn.siques.mangocommon.utils.JwtUserDetails;
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

        // 把属性放进userdetails
//        return new JwtUserDetails(user.getName(),user.getPassword(),user.getSalt(),collect);
        return null;
    }
}


