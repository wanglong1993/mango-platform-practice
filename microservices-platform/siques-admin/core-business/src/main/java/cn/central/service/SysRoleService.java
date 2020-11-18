package cn.central.service;


import cn.central.controller.dto.RoleMenuDto;
import cn.central.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysRoleService extends IService<SysRole> {

    List<SysRole>  findAll();

    SysRole findRoleMenus(Long id);

    List<SysRole> findByName(String name);

    int saveRoleMenus(String name, RoleMenuDto roleMenuDto);


}
