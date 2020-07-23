package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysRoleService;
import cn.siques.mangocore.dao.SysRoleMapper;
import cn.siques.mangocore.dao.SysUserMapper;
import cn.siques.mangocore.dao.SysUserRoleMapper;
import cn.siques.mangocore.entity.*;
import cn.siques.mango.service.SysMenuService;
import cn.siques.mango.service.SysUserService;
import cn.siques.mangocore.Page.MybatisPageHelper;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
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



    /**
     * 保存用户
     * @param record
     * @return
     */
    @Override
    public int save(SysUser record) {
        Long id = null;
        if(record.getId() == null || record.getId() == 0) {
            // 新增用户
            sysUserMapper.insertSelective(record);
            id = record.getId();
        } else {
            // 更新用户信息
            sysUserMapper.updateByPrimaryKeySelective(record);
        }
        // 更新用户角色
//        if(id != null && id == 0) {
//            return 1;
//        }
//        if(id != null) {
//            for(SysUserRole sysUserRole:record.getUserRoles()) {
//                sysUserRole.setUserId(id);
//            }
//        } else {
//            if(!record.getUserRoles().isEmpty()) {
//                sysUserRoleMapper.deleteByUserId(record.getId());
//            }
//        }
//        for(SysUserRole sysUserRole:record.getUserRoles()) {
//            sysUserRoleMapper.insertSelective(sysUserRole);
//        }
        return 1;
    }

    @Override
    public int delete(SysUser record) {
        return 0;
    }

    @Override
    public int delete(List<SysUser> records) {
        return 0;
    }

    @Override
    public SysUser findById(Long id) {
        SysUserKey sysUserKey = new SysUserKey();
        sysUserKey.setId(id);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(sysUserKey);
        return sysUser;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper,"findPageWithDept");
    }

    @Override
    public PageResult findUsersByDeptId(PageRequest pageRequest ,Long id) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper,"findUsersByDeptId",id);
    }
}
