package cn.siques.mango.service;

import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysRoleMenu;
import cn.siques.mangocore.service.CrudService;

import java.util.Collection;
import java.util.List;


public interface SysRoleService extends CrudService<SysRole> {

    List<SysRole>  findAll();

    SysRole findRoleMenus(Long id);

    List<SysRole> findByName(String name);

    int saveRoleMenus(List<SysRoleMenu> records);
}
