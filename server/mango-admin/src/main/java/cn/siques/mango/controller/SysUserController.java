package cn.siques.mango.controller;

import cn.siques.mango.controller.dto.UserRoleDto;
import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.constant.SysConstants;

import cn.siques.dto.JsonData;
import cn.siques.mango.entity.SysRole;
import cn.siques.mango.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import cn.siques.mango.entity.SysUserRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@RestController
@RequestMapping("/api/sys/v1/pri/user")
@Api(description = "SysUserController", tags = {"用户接口"})
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PreAuthorize("hasPermission('sys:user:edit')")
    @PostMapping("/saveRole")
    @ApiOperation(value = "保存用户角色关系", notes = "保存用户角色关系")
    public JsonData saveRole(@RequestBody List<UserRoleDto> userRoleList){
        SysUser user = sysUserService.getById(userRoleList.get(0).getUserId());
        if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())){
            return JsonData.buildError("管理员不允许修改");
        }

        // 删除已有的记录
       int i = sysUserService.delUserRoleByUserId(user.getId());
        for (UserRoleDto ur:userRoleList
             ) {

            SysUserRole sysUserRole = new SysUserRole(ur.getUserId(),
                    ur.getRoleId());

             sysUserService.saveUserRole(sysUserRole);
        }

        return JsonData.buildSuccess();
    }


    @PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
    @PostMapping("/save")
    public JsonData save(@RequestBody SysUser record){

        SysUser user = sysUserService.getById(record.getId());
         if(user!=null){
             if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())){
                 return JsonData.buildError("不允许修改超级管理员");
             }
         }
             // 提供密码的情况
             if(record.getPassword()!=null){
                record.setPassword(passwordEncoder.encode(record.getPassword()));
             }
        // 更新其他字段
             return JsonData.buildSuccess(sysUserService.saveOrUpdate(record));
    }



    @GetMapping("list")
    @RolesAllowed("admin")
    public JsonData findAll(){
        List<SysUser> all = sysUserService.findAll();
        return JsonData.buildSuccess(all);
    }


    /**
     * 一个用户可能含有多个角色
     * @param id
     * @return
     */
    @ApiOperation(httpMethod="GET", value="根据id查询用户角色")
    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value = "findUserRoles/{id}")
    public JsonData findUserRoles(@PathVariable("id") long id){
       List<SysRole> sysRole = sysUserService.findUserRolesById(id);
       return JsonData.buildSuccess(sysRole);
    }

    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping("/findPage")
    public JsonData findPage(@RequestBody PageRequest pageRequest){
        PageResult page = sysUserService.findPage(pageRequest);
        return JsonData.buildSuccess(page);
    }


//    @PreAuthorize("hasAuthority('sys:user:view')")
    @PostMapping("/findDeptUser/{id}")
    public JsonData findDeptUser(@PathVariable("id") Long id,@RequestBody PageRequest pageRequest){

       PageResult page   = sysUserService.findUsersByDeptId(pageRequest,id);
    return JsonData.buildSuccess(page);
    }


}
