package cn.siques.mango.service;

import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.service.CrudService;

import java.util.List;

public interface SysMenuService extends CrudService<SysMenu> {

    int save(SysMenu record);

    List<SysMenu>  findTree(String username, int menuType);

    List<SysMenu> findByUser(String name);
}
