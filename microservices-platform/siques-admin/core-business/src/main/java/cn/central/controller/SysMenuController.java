package cn.central.controller;


import cn.central.common.dto.JsonData;
import cn.central.common.utils.SecurityUtils;
import cn.central.entity.SysMenu;
import cn.central.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sys/v1/pri/menu")
@Api(description = "SysMenuController", tags = {"菜单数据表"})
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;


    @PostMapping(value="/save")
    @ApiOperation(value = "保存菜单树，需要添加或编辑权限")
    @CacheEvict(value="findMenuTree",allEntries=true)
    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
    public JsonData save(@RequestBody SysMenu record) {
        return JsonData.buildSuccess(sysMenuService.saveOrUpdate(record));
    }

    /**
     * 查询导航菜单
     * @param
     * @return
     */
//    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findNavTree")
    @ApiOperation(value = "查询导航菜单")
    public JsonData findNavTree() {
        String username = SecurityUtils.getUsername();
        return JsonData.buildSuccess(sysMenuService.findTree(username, 1));
    }

    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping("delete")
    @CacheEvict(value="findMenuTree",allEntries=true)
    @ApiOperation(value = "通过id批量删除菜单")
    public JsonData delete(@RequestBody List<SysMenu> records){
        if(records.size()<=1){
          return JsonData.buildSuccess(sysMenuService.removeIdAndChild(records.get(0).getId()));
        }
        return JsonData.buildSuccess(sysMenuService.removeByIds(records));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findMenuTree")
    @ApiOperation(value = "查询菜单树")
    public JsonData findMenuTree() {
        return JsonData.buildSuccess(sysMenuService.findTree("", 0));
    }


}
