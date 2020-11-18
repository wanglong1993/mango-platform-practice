package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysLoginLogService;

import cn.siques.Page.MybatisPageHelper;
import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.utils.SecurityUtils;
import cn.siques.mango.dao.SysLoginLogMapper;
import cn.siques.mango.entity.SysLoginLog;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper,SysLoginLog> implements SysLoginLogService {

    @Resource
    SysLoginLogMapper sysLoginLogMapper;

    @Transactional
    @Override
    public void writeLoginLog(String userName, String ip) {
        // 查询所有的用户登录状态
        List<SysLoginLog> sysLoginLogs = sysLoginLogMapper.findByUserName(userName);
        for(SysLoginLog sysLoginLog:sysLoginLogs) {
            sysLoginLog.setStatus(SysLoginLog.STATUS_ONLINE);
            sysLoginLogMapper.updateById(sysLoginLog);
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
            sysLoginLogMapper.updateById(sysLoginLog);
        }
    }


    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysLoginLogMapper);
        return page;
    }
}
