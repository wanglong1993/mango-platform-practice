package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysLoginLogService;

import cn.siques.mangocommon.Page.MybatisPageHelper;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.utils.SecurityUtils;
import cn.siques.mangocore.dao.SysLoginLogMapper;
import cn.siques.mangocore.entity.SysLoginLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    SysLoginLogMapper sysLoginLogMapper;

    @Transactional
    @Override
    public void writeLoginLog(String userName, String ip) {
        // 查询所有的用户登录状态
        List<SysLoginLog> sysLoginLogs = sysLoginLogMapper.findByUserName(userName);
        for(SysLoginLog sysLoginLog:sysLoginLogs) {
            sysLoginLog.setStatus(SysLoginLog.STATUS_ONLINE);
            sysLoginLogMapper.updateByPrimaryKey(sysLoginLog);
        }
        if(sysLoginLogs.size()==0){
            SysLoginLog sysLoginLog = new SysLoginLog();
            sysLoginLog.setStatus(SysLoginLog.STATUS_ONLINE);
            sysLoginLog.setIp(ip);
            sysLoginLog.setUserName(userName);
            sysLoginLog.setCreateTime(new Date());
            sysLoginLog.setCreateBy(SecurityUtils.getUsername());
            this.save(sysLoginLog);
        }
    }

    @Override
    public void loginOutLog(String username, String ipAddr) {
        List<SysLoginLog> sysLoginLogs = sysLoginLogMapper.findByUserName(username);
        for(SysLoginLog sysLoginLog:sysLoginLogs) {
            sysLoginLog.setStatus(SysLoginLog.STATUS_LOGOUT);
            sysLoginLogMapper.updateByPrimaryKey(sysLoginLog);
        }
    }

    @Override
    public int save(SysLoginLog record) {
        int insert = sysLoginLogMapper.insert(record);
        return insert;
    }

    @Override
    public int delete(SysLoginLog record) {
        return 0;
    }

    @Override
    public int delete(List<SysLoginLog> records) {
        return 0;
    }

    @Override
    public SysLoginLog findById(Long id) {
        return null;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper);
        return page;
    }
}
