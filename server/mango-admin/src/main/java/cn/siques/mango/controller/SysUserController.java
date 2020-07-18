package cn.siques.mango.controller;

import cn.siques.mangocore.entity.JsonData;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@RestController
@RequestMapping("/api/sys/v1/pri/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("list")
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


    @PostMapping("/findPage")
    public JsonData findPage(@RequestBody PageRequest pageRequest){
        PageResult page = sysUserService.findPage(pageRequest);
        System.out.println(page);
        return JsonData.buildSuccess(page);
    }



}
