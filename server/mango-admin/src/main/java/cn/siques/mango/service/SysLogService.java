package cn.siques.mango.service;

import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SysLogService extends IService<SysLog> {

    PageResult findPage(PageRequest pageRequest);
}
