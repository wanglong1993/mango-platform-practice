package cn.central.service;


import cn.central.common.model.SysRole;
import cn.central.controller.dto.RoleMenuDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysRoleService extends IService<SysRole> {


    SysRole findRoleMenus(Long id);

    SysRole findByRoleCode(String name);

    int saveRoleMenus(String name, RoleMenuDto roleMenuDto);


}
