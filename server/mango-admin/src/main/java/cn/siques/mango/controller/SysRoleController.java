package cn.siques.mango.controller;

import cn.siques.mango.config.RedisUtils;
import cn.siques.mango.controller.dto.RoleMenuDto;
import cn.siques.mango.service.SysRoleService;
import cn.siques.mango.service.SysUserService;
import cn.siques.mangocommon.constant.SysConstants;
import cn.siques.mangocommon.dto.JsonData;
import cn.siques.mangocore.dao.SysRoleMapper;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysRoleMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Api(tags = {"角色管理接口"})
@RequestMapping("/api/sys/v1/pri/role")
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils<String,String> redisUtils;

    @ApiOperation(httpMethod="GET", value="查询所有角色")
    @GetMapping("findAll")
    @PreAuthorize("hasAuthority('sys:role:view')")
    public JsonData findAll(){
        return JsonData.buildSuccess(sysRoleService.findAll());
    }


    @ApiOperation(httpMethod="POST", value="保存角色信息")
    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    @PostMapping(value = "/save")
    public JsonData save(@RequestBody SysRole sysRole){
        // 先去查数据库
        SysRole role = sysRoleService.getById(sysRole.getId());
        if(role!=null){
            if(SysConstants.ADMIN.equalsIgnoreCase(role.getName())){
                return JsonData.buildError("管理员不可修改");
            }
        }
        // 新增
        if(sysRole.getId()==null || sysRole.getId()==0 && !sysRoleService.findByName(sysRole.getName()).isEmpty()){
            return JsonData.buildError("重复角色名");
        }
        return JsonData.buildSuccess(sysRoleService.saveOrUpdate(sysRole));
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
     * @param
     * @return
     */
    @ApiOperation(httpMethod="POST", value="修改角色权限")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @PostMapping(value="/saveRoleMenus")

    public JsonData saveRoleMenus(@RequestBody() RoleMenuDto roleMenuDto) {

        SysRole role = sysRoleMapper.selectByPrimaryKey(roleMenuDto.getRoleId());

            if(SysConstants.ADMIN.equalsIgnoreCase(role.getName()))
                return JsonData.buildError("超级管理员拥有所有权限，不可修改");

       // 修改角色的权限
        return JsonData.buildSuccess(sysRoleService.saveRoleMenus(role.getName(),roleMenuDto));

    }

}
