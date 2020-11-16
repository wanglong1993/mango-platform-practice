package cn.siques.mangooauth.service.impl;

/**
 * @author : heshenghao
 * @date : 18:58 2020/11/13
 */


import cn.siques.mangooauth.dao.SysMenuDao;
import cn.siques.mangooauth.dao.SysUserDao;
import cn.siques.mangooauth.entity.SysMenu;
import cn.siques.mangooauth.entity.SysUser;
import cn.siques.mangooauth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
