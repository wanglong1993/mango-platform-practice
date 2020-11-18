package cn.central.service;


import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.entity.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SysLoginLogService extends IService<SysLoginLog> {
    void writeLoginLog(String username, String ipAddr);

    void loginOutLog(String username, String ipAddr);
    PageResult findPage(PageRequest pageRequest);
}
