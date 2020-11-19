package cn.central.oauth.service.impl;

/**
 * @author : heshenghao
 * @date : 18:58 2020/11/13
 */


import cn.central.oauth.dao.SysMenuDao;
import cn.central.oauth.dao.SysUserDao;
import cn.central.oauth.entity.SysUser;
import cn.central.oauth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Autowired
    SysUserDao sysUserDao;

    @Autowired
    SysMenuDao sysMenuDao;

    @Override
    public SysUser findUserByName(String username) {
        SysUser sysUser = sysUserDao.findUserByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        if(sysUser.getName().equals("admin")) sysUser.setMenus(sysMenuDao.findAllMenus());
        return sysUser;
    }

}
