package cn.siques.mango.controller;

import cn.siques.mangocommon.constant.SysConstants;
import cn.siques.mango.service.SysRoleService;
import cn.siques.mangocore.dao.SysRoleMapper;
import cn.siques.mangocore.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;

@RestController
@Api(value="角色管理接口")
@RequestMapping("/api/sys/v1/pri/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @ApiOperation(httpMethod="GET", value="查询所有角色")
    @GetMapping("findAll")
    // 超级管理员接口
    @RolesAllowed("admin")
    public JsonData findAll(){
        return JsonData.buildSuccess(sysRoleService.findAll());
    }


    @ApiOperation(httpMethod="POST", value="保存角色信息")
    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    @PostMapping(value = "/save")
    public JsonData save(@RequestBody SysRole sysRole){
        // 先去查数据库
        SysRole role = sysRoleService.findById(sysRole.getId());
        if(role!=null){
            if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())){
                return JsonData.buildError("管理员不可修改");
            }
        }
        // 新增
        if(sysRole.getId()==null || sysRole.getId()==0 && !sysRoleService.findByName(sysRole.getName()).isEmpty()){
            return JsonData.buildError("重复角色名");
        }
        return JsonData.buildSuccess(sysRoleService.save(sysRole));
    }

    @ApiOperation(httpMethod="GET", value="根据id查询角色菜单")
    @GetMapping(value="findRoleMenus/{id}")
    @PreAuthorize("hasAuthority('sys:role:view')")
    public JsonData  findRoleMenus(@PathVariable("id") Long id){
        SysRole sysMenus= sysRoleService.findRoleMenus(id);
        return JsonData.buildSuccess(sysMenus);
    }



    /**
     * 修改用户菜单关系
     * @param records
     * @return
     */
    @ApiOperation(httpMethod="POST", value="修改角色权限")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/saveRoleMenus")
    public JsonData saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        SysRoleKey sysRoleKey = new SysRoleKey();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        System.out.println(authorities);

        for (SysRoleMenu record:records
             ) {
            sysRoleKey.setId(record.getRoleId());
            SysRole role = sysRoleMapper.selectByPrimaryKey(sysRoleKey);
            if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())){
                // 如果是admin 不修改
                return JsonData.buildError("超级管理员拥有所有权限，不可修改");
            }
            // 如果能运行到这，说明不是管理员，可以跳过之后的循环
            break;
        }
        return JsonData.buildSuccess(sysRoleService.saveRoleMenus(records));
    }

}
