package cn.siques.mango.service;

import cn.siques.mango.controller.dto.RoleMenuDto;
import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.entity.SysRole;
import cn.siques.mangocore.entity.SysRoleMenu;
import cn.siques.mangocore.service.CrudService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface SysRoleService extends IService<SysRole> {

    List<SysRole>  findAll();

    SysRole findRoleMenus(Long id);

    List<SysRole> findByName(String name);

    int saveRoleMenus(String name, RoleMenuDto roleMenuDto);


}
