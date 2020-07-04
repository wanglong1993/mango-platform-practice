package cn.siques.mangomenu.service;


import cn.siques.mangocore.entity.SysMenu;

import java.util.List;

public interface SysMenuService {
    int save(SysMenu record);

    List<SysMenu>  findTree(String username, int menuType);
}
