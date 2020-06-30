package cn.siques.mango.service;

import cn.siques.mango.entity.SysUser;
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
}
