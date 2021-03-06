package cn.siques.mango.service;


import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.entity.SysRole;
import cn.siques.mango.entity.SysUser;
import cn.siques.mango.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * CrudService已经有findpage方法
 */
public interface SysUserService extends IService<SysUser> {
    List<SysUser> findAll();


    PageResult findPage(PageRequest pageRequest);

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

    List<SysUser> findUsersByRoleId(Long id);
}
