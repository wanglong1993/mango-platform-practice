package cn.siques.mangomenu.controller;

import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.http.JsonData;
import cn.siques.mangomenu.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sys/v1/pri/menu")
public class SysMenuController {

    @Autowired
    SysMenuService sysMenuService;

    @PostMapping(value="/save")
    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
    public JsonData save(@RequestBody SysMenu record) {
        return JsonData.buildSuccess(sysMenuService.save(record));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findMenuTree")

    public JsonData findMenuTree() {
        return JsonData.buildSuccess(sysMenuService.findTree(null, 0));
    }


}
