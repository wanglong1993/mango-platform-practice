package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysLoginLogService;
import cn.siques.mangocore.Page.MybatisPageHelper;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import cn.siques.mangocore.dao.SysLoginLogMapper;
import cn.siques.mangocore.entity.SysLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return 0;
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
