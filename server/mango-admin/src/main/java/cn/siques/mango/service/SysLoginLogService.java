package cn.siques.mango.service;

import cn.siques.mangocore.entity.SysLoginLog;
import cn.siques.mangocore.service.CrudService;
import org.springframework.stereotype.Service;


public interface SysLoginLogService extends CrudService<SysLoginLog> {
    void writeLoginLog(String username, String ipAddr);

    void loginOutLog(String username, String ipAddr);
}
