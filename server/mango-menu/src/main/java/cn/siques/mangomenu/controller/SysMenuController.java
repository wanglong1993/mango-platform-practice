package cn.siques.mangomenu.controller;

import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.http.JsonData;
import cn.siques.mangocore.utils.SecurityUtils;
import cn.siques.mangomenu.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    /**
     * 查询导航菜单树
     * 这里所有人可查，去auth上下文里拿name
     * @param
     * @return
     */
//    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findNavTree")
    public JsonData findNavTree() {
        String username = SecurityUtils.getUsername();
        System.out.println(username);
        return JsonData.buildSuccess(sysMenuService.findTree(username, 1));
    }

    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping("delete")
    public JsonData delete(@RequestBody List<SysMenu> records){
        return JsonData.buildSuccess(sysMenuService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findMenuTree")
    public JsonData findMenuTree() {
        return JsonData.buildSuccess(sysMenuService.findTree(null, 0));
    }


}
