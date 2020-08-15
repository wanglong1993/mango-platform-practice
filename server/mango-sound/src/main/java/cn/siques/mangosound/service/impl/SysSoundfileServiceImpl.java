package cn.siques.mangosound.service.impl;


import cn.siques.mangocommon.Page.MybatisPageHelper;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.utils.ReflectionUtils;
import cn.siques.mangosound.config.OssConfig;
import com.aliyun.oss.model.OSSObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.siques.mangosound.entity.SysSoundfile;
import cn.siques.mangosound.mapper.SysSoundfileMapper;
import cn.siques.mangosound.service.SysSoundfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    OssConfig ossConfig;
    @Autowired
    SysSoundfileMapper sysSoundfileMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysSoundfileMapper);
        return page;
    }

    @Override
    public OSSObject getIsFromOss(Integer id) {
        SysSoundfile file = sysSoundfileMapper.selectById(id);
        String reaPath= file.getPath()+"/"+file.getName()+file.getExt();
        OSSObject ossObject = ossConfig.getOssObject(reaPath);

        return  ossObject;
    }

    @Override
    public PageResult searchSoundfile(SysSoundfile sysSoundfile, PageRequest pageRequest) {
        // 获取可以get到的参数
        Map<String, String> map = ReflectionUtils.getMap(sysSoundfile);

        PageResult searchSoundfile = MybatisPageHelper.findPage(pageRequest, sysSoundfileMapper, "searchSoundfile", map);
//        List<SysSoundfile> sysSoundfiles = sysSoundfileMapper.searchSoundfile(map);
        return searchSoundfile;
    }




}
