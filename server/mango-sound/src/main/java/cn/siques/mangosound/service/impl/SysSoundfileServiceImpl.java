package cn.siques.mangosound.service.impl;


import cn.siques.mangocommon.Page.MybatisPageHelper;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.siques.mangosound.entity.SysSoundfile;
import cn.siques.mangosound.mapper.SysSoundfileMapper;
import cn.siques.mangosound.service.SysSoundfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 
 * </p>
 *
 * @package: cn.siques.mangosound.service.impl
 * @description: 
 * @author: Shenghao.He
 * @date: Created in 2020-08-08 17:28:56
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@Service
public class SysSoundfileServiceImpl extends ServiceImpl<SysSoundfileMapper, SysSoundfile> implements SysSoundfileService {


    @Autowired
    SysSoundfileMapper sysSoundfileMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysSoundfileMapper);
        return page;
    }
}
