package cn.siques.mango.service;

import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.service.CrudService;

import java.util.List;

public interface SysMenuService extends CrudService<SysMenu> {


    List<SysMenu> findByUser(String name);
}
