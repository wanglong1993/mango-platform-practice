package cn.central.service;



import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;

import cn.central.common.model.SysRole;
import cn.central.common.model.SysUser;
import cn.central.entity.SysUserRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * CrudService已经有findpage方法
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据code查找权限
     * @param userCode
     * @return
     */
    Set<String> findPermission(String userCode);

    /**
     * 根据id查询用户角色
     * @param id
     * @return
     */
    List<SysRole> findUserRolesById(Long id);

    IPage<SysUser> findUsersByOfficeId(IPage page, Long deptId);

    int saveUserRole(SysUserRole sysUserRole);

    int delUserRoleByUserId(Long id);


}
