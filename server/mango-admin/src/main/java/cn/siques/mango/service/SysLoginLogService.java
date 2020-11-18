package cn.siques.mango.service;

import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.entity.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;



public interface SysLoginLogService extends IService<SysLoginLog> {
    void writeLoginLog(String username, String ipAddr);

    void loginOutLog(String username, String ipAddr);
    PageResult findPage(PageRequest pageRequest);
}
