package cn.siques.mango.service;


import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysUser;
import cn.siques.mangocore.entity.SysUserRole;
import cn.siques.mangocore.service.CrudService;

import java.util.List;
import java.util.Set;

/**
 * CrudService已经有findpage方法
 */
public interface SysUserService extends CrudService<SysUser> {
    List<SysUser> findAll();


    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    SysUser findByName(String username);

    /**
     * 根据用户名查找权限
     * @param name
     * @return
     */
    Set<String> findPermission(String name);

    /**
     * 根据id查询用户角色
     * @param id
     * @return
     */
    List<SysRole> findUserRolesById(Long id);


    int saveUserRole(SysUserRole sysUserRole);

    int delUserRoleByUserId(Long id);

    PageResult findUsersByDeptId(PageRequest pageRequest, Long id);
}
