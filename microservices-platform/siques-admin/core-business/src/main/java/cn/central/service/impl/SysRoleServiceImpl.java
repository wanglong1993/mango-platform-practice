package cn.central.service.impl;


import cn.central.common.constant.AdminConstants;
import cn.central.common.model.SysRole;
import cn.central.controller.dto.RoleMenuDto;
import cn.central.dao.SysMenuMapper;
import cn.central.dao.SysRoleMapper;
import cn.central.dao.SysRoleMenuMapper;

import cn.central.entity.SysRoleMenu;
import cn.central.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    SysRoleMapper sysRoleMapper;

    @Resource
    SysMenuMapper sysMenuMapper;

    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public SysRole findRoleMenus(Long roleId) {
        SysRole sysRole = sysRoleMapper.selectById(roleId);
        if(AdminConstants.ADMIN.equalsIgnoreCase(sysRole.getRoleCode())) {
            // 如果是超级管理员，返回全部的权限
            sysRole.setSysMenuList(sysMenuMapper.selectList(new QueryWrapper<>()));
            return sysRole;
        }
        sysRole.setSysMenuList(sysMenuMapper.findRoleMenus(roleId));
        return sysRole;
    }

    @Override
    public SysRole findByRoleCode(String roleCode) {
        return sysRoleMapper.findByRoleCode(roleCode);
    }

    /**
     * 修改角色菜单权限，需要同时清空该用户的菜单缓存
     * @param
     * @return
     */
    @Transactional
    @Override
    @CacheEvict(value="findMenuTree",key="#roleName")
    public int saveRoleMenus(String roleName, RoleMenuDto roleMenuDto) {

        // 清除已有的的菜单
        sysRoleMenuMapper.deleteByRoleId(roleMenuDto.getRoleId());
        // 插入新菜单
        for(SysRoleMenu record:roleMenuDto.getRoleMenus()) {
            sysRoleMenuMapper.insert(record);
        }
        return 1;
    }

}
