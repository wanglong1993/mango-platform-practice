package cn.siques.mango.service.impl;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.constant.SysConstants;
import cn.siques.mangocore.dao.SysMenuMapper;
import cn.siques.mangocore.entity.SysMenu;
import cn.siques.mango.service.SysMenuService;

import cn.siques.mangocore.entity.SysMenuKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SysMenuServiceImmpl implements SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    @CacheEvict(value="findMenuTree",allEntries=true)
    public int save(SysMenu record) {
        if(record.getId() == null || record.getId() == 0) {
            return sysMenuMapper.insertSelective(record);
        }
        if(record.getParentId() == null) {
            record.setParentId(0L);
        }
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysMenu record) {
        SysMenuKey sysMenuKey = new SysMenuKey();
        sysMenuKey.setId(record.getId());
        return sysMenuMapper.deleteByPrimaryKey(sysMenuKey);
    }


    /**
     * 删除菜单是刷新所有菜单缓存
     * @param records
     * @return
     */
    @Override
    @CacheEvict(value="findMenuTree",allEntries=true)
    public int delete(List<SysMenu> records) {
        for(SysMenu record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysMenu findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }

    @Override
    @Cacheable(cacheNames = {"findMenuTree"})
    public List<SysMenu> findTree(String username, int menuType) {
        List<SysMenu> sysMenus = new ArrayList<>();
        // 用户所有的
        List<SysMenu> menus = findByUser(username);

        for(SysMenu menu:menus){
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                if(!exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                }
            }
        }

        sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        findChildren(sysMenus, menus, menuType);
        return sysMenus;
    }

    private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu SysMenu : SysMenus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if(menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue ;
                }
                if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
                    menu.setParentName(SysMenu.getName());
                    menu.setLevel(SysMenu.getLevel() + 1);
                    if(!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }

            if(children.size()!=0){

                SysMenu.setChildren(children);
            }

            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }
    }


    private boolean exists(List<SysMenu> sysMenus, SysMenu sysMenu) {
        boolean exist = false;
        for(SysMenu menu:sysMenus) {
            if(menu.getId().equals(sysMenu.getId())) {
                exist = true;
            }
        }
        return exist;
    }



    @Override
    public List<SysMenu> findByUser(String name) {
        if(name==null || "".equals(name)|| SysConstants.ADMIN.equalsIgnoreCase(name)){
            // 如果是超级管理员返回所有权限
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUserName(name);
    }
}
