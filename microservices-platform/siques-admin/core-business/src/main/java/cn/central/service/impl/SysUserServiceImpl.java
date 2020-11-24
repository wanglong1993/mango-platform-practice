package cn.central.service.impl;


import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.common.constant.AdminConstants;
import cn.central.common.model.SysRole;
import cn.central.common.model.SysUser;
import cn.central.dao.SysMenuMapper;
import cn.central.dao.SysUserMapper;
import cn.central.dao.SysUserRoleMapper;

import cn.central.entity.SysUserRole;
import cn.central.service.SysMenuService;
import cn.central.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysUserServiceImpl  extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    SysMenuMapper sysMenuMapper;

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    /**
     * 根据code查找菜单，再根据菜单查询权限
     * @param userCode
     * @return
     */
    @Override
    public Set<String> findPermission(String userCode) {
        Set<String> perms = sysMenuMapper.findUserMenuByUserCode(userCode).stream()
                .filter(sysMenu -> sysMenu.getPerms() != null && !"".equals(sysMenu.getPerms()))
                .map(sysMenu -> sysMenu.getPerms()).collect(Collectors.toSet());
        return perms;
    }

    @Override
    public  List<SysRole> findUserRolesById(Long id) {
        return sysUserMapper.findUserRolesById(id);
    }

    @Override
    public IPage<SysUser> findUsersByOfficeId(IPage page, Long deptId) {
        return sysUserMapper.findUsersByOfficeId(page,deptId);
    }

    @Override
    public int saveUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public int delUserRoleByUserId(Long id) {
        return sysUserRoleMapper.deleteByUserId(id);
    }




}
