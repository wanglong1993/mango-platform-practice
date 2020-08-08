package cn.siques.mango.config.security;

import cn.siques.mangocommon.utils.GrantedAuthorityImpl;
import cn.siques.mangocommon.utils.JwtUserDetails;
import cn.siques.mangocore.entity.SysUser;
import cn.siques.mango.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数据库操作
 */
@Service("myService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      SysUser user =  sysUserService.findByName(username);
        if (user == null) {
        throw  new UsernameNotFoundException("用户不存在");
        }
        // 用户权限列表
      Set<String> permission= sysUserService.findPermission(user.getName());
        List<GrantedAuthorityImpl> collect = permission.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());

        // 把属性放进userdetails
        return new JwtUserDetails(user.getName(),user.getPassword(),user.getSalt(),collect);
        }
}

