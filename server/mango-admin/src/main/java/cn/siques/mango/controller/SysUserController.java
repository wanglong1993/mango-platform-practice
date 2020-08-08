package cn.siques.mango.controller;

import cn.siques.mango.controller.dto.UserRoleDto;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.constant.SysConstants;

import cn.siques.mangocommon.dto.JsonData;
import cn.siques.mangocommon.utils.PasswordUtils;
import cn.siques.mangocommon.utils.SecurityUtils;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import cn.siques.mangocore.entity.SysUserRole;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@RestController
@RequestMapping("/api/sys/v1/pri/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PostMapping("/saveRole")
    public JsonData saveRole(@RequestBody List<UserRoleDto> userRoleList){
        SysUser user = sysUserService.findById(userRoleList.get(0).getUserId());
        if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())){
            return JsonData.buildError("管理员不允许修改");
        }

        // 删除已有的记录
       int i = sysUserService.delUserRoleByUserId(user.getId());
        for (UserRoleDto ur:userRoleList
             ) {

            SysUserRole sysUserRole = new SysUserRole(ur.getUserId(),
                    ur.getRoleId(), SecurityUtils.getUsername(),new Date(),SecurityUtils.getUsername(),new Date(),"");

             sysUserService.saveUserRole(sysUserRole);
        }

        return JsonData.buildSuccess();
    }


    @PreAuthorize("hasAuthority('sys:user:add') AND hasAuthority('sys:user:edit')")
    @PostMapping("/save")
    public JsonData save(@RequestBody SysUser record){
        System.out.println(record);
        SysUser user = sysUserService.findById(record.getId());
         if(user!=null){
             if(SysConstants.ADMIN.equalsIgnoreCase(user.getName())){
                 return JsonData.buildError("不允许修改超级管理员");
             }
         }
             // 提供密码的情况
             if(record.getPassword()!=null){
                 String salt =  PasswordUtils.getSalt();
                 if(user==null){
                     // 新增用户 检查是否重名
                     if(sysUserService.findByName(record.getName())!=null){
                         return JsonData.buildError("用户名已存在");
                     }
                    String password = PasswordUtils.encode(record.getPassword(),salt);
                     record.setSalt(salt);
                     record.setPassword(password);
                 }else{
                     // 若用户存在，传回来一个不同的密码，则修改
                     if(!record.getPassword().equals(user.getPassword())){
                         String password = PasswordUtils.encode(record.getPassword(), salt);
                         record.setSalt(salt);
                         record.setPassword(password);
                     }

                 }
             }
        // 更新其他字段
             return JsonData.buildSuccess(sysUserService.save(record));
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
