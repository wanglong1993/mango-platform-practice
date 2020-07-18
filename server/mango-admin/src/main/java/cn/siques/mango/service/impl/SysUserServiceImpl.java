package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysRoleService;
import cn.siques.mangocore.dao.SysRoleMapper;
import cn.siques.mangocore.dao.SysUserMapper;
import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysRoleKey;
import cn.siques.mangocore.entity.SysUser;
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
    public   List<SysRole> findUserRolesById(long id) {

        List<SysRole> sysRole = sysUserMapper.findUserRolesById(id);
        return sysRole;
    }

    /**
     * 保存用户
     * @param record
     * @return
     */
    @Override
    public int save(SysUser record) {
        int insert = sysUserMapper.insert(record);
        return insert;
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
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysUserMapper);
    }
}
