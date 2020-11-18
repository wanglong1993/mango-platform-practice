package cn.central.service;

import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SysLogService extends IService<SysLog> {

    PageResult findPage(PageRequest pageRequest);
}
