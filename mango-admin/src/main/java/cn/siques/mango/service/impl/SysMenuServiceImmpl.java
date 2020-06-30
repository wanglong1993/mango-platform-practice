package cn.siques.mango.service.impl;

import cn.siques.mango.constant.SysConstants;
import cn.siques.mango.dao.SysMenuMapper;
import cn.siques.mango.entity.SysConfig;
import cn.siques.mango.entity.SysMenu;
import cn.siques.mango.service.SysMenuService;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysMenuServiceImmpl implements SysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public int save(SysMenu record) {
        return 0;
    }

    @Override
    public int delete(SysMenu record) {
        return 0;
    }

    @Override
    public int delete(List<SysMenu> records) {
        return 0;
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
    public List<SysMenu> findByUser(String name) {
        if(name==null || "".equals(name)|| SysConstants.ADMIN.equalsIgnoreCase(name)){
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUserName(name);
    }
}
