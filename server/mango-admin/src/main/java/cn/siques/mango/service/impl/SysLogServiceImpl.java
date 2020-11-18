package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysLogService;

import cn.siques.Page.MybatisPageHelper;
import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.dao.SysLogMapper;
import cn.siques.mango.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper,SysLog> implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysLogMapper);
        return page;
    }
}
