package cn.central.service.impl;


import cn.central.common.Page.MybatisPageHelper;
import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.dao.SysUserMapper;
import cn.central.dao.SysUserRoleMapper;
import cn.central.entity.SysMenu;
import cn.central.entity.SysRole;
import cn.central.entity.SysUser;
import cn.central.entity.SysUserRole;
import cn.central.service.SysMenuService;
import cn.central.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysMenuService sysMenuService;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;


    @Override
    public List<SysUser> findAll() {
        List<SysUser> all = sysUserMapper.findAll();
        return all;
    }

    @Override
    public SysUser findByName(String username) {

        return  sysUserMapper.findByName(username);
    }

    /**
     * 根据名字查找菜单，再根据菜单查询权限
     * @param name
     * @return
     */
    @Override
    public Set<String> findPermission(String name) {
        Set<String> perms = new HashSet<>();
        List<SysMenu> sysMenus = sysMenuService.findByUser(name);
        for (SysMenu sysMenu:sysMenus
             ) {
            if(sysMenu.getPerms()!=null &&!"".equals(sysMenu.getPerms())){
                perms.add(sysMenu.getPerms());
            }
        }
        return perms;
    }

    @Override
    public   List<SysRole> findUserRolesById(Long id) {

        List<SysRole> sysRole = sysUserMapper.findUserRolesById(id);
        return sysRole;
    }

    @Override
    public int saveUserRole(SysUserRole sysUserRole) {
        int insert = sysUserRoleMapper.insert(sysUserRole);
        return insert;
    }

    @Override
    public int delUserRoleByUserId(Long id) {

        int i = sysUserRoleMapper.deleteByUserId(id);
        return i;
    }



    public SysUser findById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        return sysUser;
    }


    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper,"findPageWithDept");
    }

    @Override
    public PageResult findUsersByDeptId(PageRequest pageRequest ,Long id) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper,"findUsersByDeptId",id);
    }

    @Override
    public List<SysUser> findUsersByRoleId(Long id) {
        return sysUserMapper.findUsersByRoleId(id);
    }
}
