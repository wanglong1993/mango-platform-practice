package cn.central.controller;


import cn.central.common.constant.AdminConstants;

import cn.central.common.model.Result;
import cn.central.common.model.SysRole;
import cn.central.controller.dto.RoleMenuDto;
import cn.central.dao.SysRoleOfficeMapper;
import cn.central.dao.SysRoleMapper;

import cn.central.dao.SysRoleMenuMapper;
import cn.central.entity.SysRoleOffice;
import cn.central.service.SysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = {"角色管理接口"})
@RequestMapping("/api/v1/pri/role")
public class SysRoleController {
    @Resource
    SysRoleService sysRoleService;
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleOfficeMapper sysRoleOfficeMapper;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;


    @ApiOperation(httpMethod="GET", value="查询所有角色")
    @GetMapping()
    @PreAuthorize("@el.check('sys:role:view')")
    public Result findAll(){
        return Result.succeed(sysRoleService
                .list(new QueryWrapper<SysRole>().orderByAsc("role_sort"))
        );
    }


    @ApiOperation(httpMethod="POST", value="保存角色信息")
    @PreAuthorize("@el.check('sys:role:edit')")
    @PostMapping()
    public Result save(@RequestBody SysRole sysRole){
        // sysRole必须包含code，去查数据库
        SysRole role = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("role_code", sysRole.getRoleCode()));
        // 如果不存在该角色，可直接插入
        if(role==null)  return Result.succeed(sysRoleService.save(sysRole));
        // 如果该角色名为admin
        else if(AdminConstants.ADMIN.equalsIgnoreCase(role.getRoleCode())) return Result.failed("管理员不可修改");
        // 如果传入角色编码则更新
        else return Result.succeed(sysRoleService.update
                    (sysRole,new UpdateWrapper<SysRole>().eq("role_code",sysRole.getRoleCode())));
    }

    @ApiOperation(httpMethod="DELETE", value="删除角色，附带删除角色菜单关系")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@el.check('sys:role:delete')")
    @Transactional
    public Result delete(@PathVariable("id") Long id){
        // 删除关联关系
        sysRoleOfficeMapper.delete(new QueryWrapper<SysRoleOffice>().eq("role_id",id));
        sysRoleMenuMapper.deleteByRoleId(id);
        return Result.succeed(sysRoleService.removeById(id));
    }


    @ApiOperation(httpMethod="GET", value="根据id查询角色菜单")
    @GetMapping(value="findRoleMenus/{id}")
    @PreAuthorize("@el.check('sys:role:view')")
    public Result  findRoleMenus(@PathVariable("id") Long id){
        SysRole sysMenus= sysRoleService.findRoleMenus(id);
        return Result.succeed(sysMenus);
    }

    /**
     * 修改用户菜单关系
     * @param
     * @return
     */
    @ApiOperation(httpMethod="POST", value="修改角色权限")
    @PreAuthorize("@el.check('sys:role:edit')")
    @PostMapping(value="/saveRoleMenus")
    public Result saveRoleMenus(@RequestBody() RoleMenuDto roleMenuDto) {

        SysRole role = sysRoleMapper.selectById(roleMenuDto.getRoleId());

            if(AdminConstants.ADMIN.equalsIgnoreCase(role.getRoleCode()))
                return Result.failed("超级管理员拥有所有权限，不可修改");

       // 修改角色的权限
        return Result.succeed(sysRoleService.saveRoleMenus(role.getRoleName(),roleMenuDto));
    }

}
