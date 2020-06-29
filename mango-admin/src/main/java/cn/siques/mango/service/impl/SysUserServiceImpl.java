package cn.siques.mango.service.impl;

import cn.siques.mango.dao.SysUserMapper;
import cn.siques.mango.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public List<SysUser> findAll() {
        List<SysUser> all = sysUserMapper.findAll();
        return all;
    }
}
