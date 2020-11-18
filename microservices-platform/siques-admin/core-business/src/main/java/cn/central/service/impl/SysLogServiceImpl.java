package cn.central.service.impl;


import cn.central.common.Page.MybatisPageHelper;
import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.dao.SysLogMapper;
import cn.central.entity.SysLog;
import cn.central.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysLogMapper);
        return page;
    }
}
