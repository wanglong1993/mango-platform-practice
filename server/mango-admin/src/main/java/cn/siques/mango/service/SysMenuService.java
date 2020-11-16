package cn.siques.mango.service;

import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mangocore.service.CrudService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu>  findTree(String username, int menuType);

    List<SysMenu> findByUser(String name);

    int removeIdAndChild(Long id);
}
