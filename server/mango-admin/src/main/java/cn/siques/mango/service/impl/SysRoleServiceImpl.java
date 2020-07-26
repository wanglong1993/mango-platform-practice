package cn.siques.mango.service.impl;

import cn.siques.mangocommon.constant.SysConstants;
import cn.siques.mango.service.SysRoleService;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import cn.siques.mangocore.dao.SysMenuMapper;
import cn.siques.mangocore.dao.SysRoleMapper;
import cn.siques.mangocore.dao.SysRoleMenuMapper;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysRoleKey;
import cn.siques.mangocore.entity.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int save(SysRole record) {
        return 0;
    }

    @Override
    public int delete(SysRole record) {
        return 0;
    }

    @Override
    public int delete(List<SysRole> records) {
        return 0;
    }

    @Override
    public SysRole findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    public List<SysRole> findAll() {
       List<SysRole> sysRoles =  sysRoleMapper.findAll();
        return sysRoles;
    }

    @Override
    public SysRole findRoleMenus(Long roleId) {
        SysRoleKey sysRoleKey = new SysRoleKey();
        sysRoleKey.setId(roleId);
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(sysRoleKey);
        if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，返回全部的权限
            sysRole.setSysMenuList(sysMenuMapper.findAll());
            return sysRole;
        }
        sysRole.setSysMenuList(sysMenuMapper.findRoleMenus(roleId));
        return sysRole;
    }

    @Override
    public List<SysRole> findByName(String name) {
        List<SysRole> sysRoles = sysRoleMapper.findByName(name);
        return sysRoles;
    }

    /**
     * 修改角色菜单权限，需要同时清空该用户的菜单缓存
     * @param records
     * @return
     */
    @Transactional
    @Override
    @CacheEvict(value="findMenuTree",key = "",allEntries=true)
    public int saveRoleMenus(List<SysRoleMenu> records) {
        if(records == null || records.isEmpty()) {
            return 1;
        }
        Long roleId = records.get(0).getRoleId();
        // 清除已有的
        sysRoleMenuMapper.deleteByRoleId(roleId);
        for(SysRoleMenu record:records) {
            sysRoleMenuMapper.insertSelective(record);
        }
        return 1;
    }


}
