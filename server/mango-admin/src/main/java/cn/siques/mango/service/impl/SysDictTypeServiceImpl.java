package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysDictTypeService;
import cn.siques.mangocommon.Page.MybatisPageHelper;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocore.dao.SysDictTypeMapper;
import cn.siques.mangocore.entity.SysDictData;
import cn.siques.mangocore.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @package: cn.siques.mangosound.service.impl
 * @description: 字典类型表
 * @author: Shenghao.He
 * @date: Created in 2020-09-03 11:22:09
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@Service
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {


    @Resource
    SysDictTypeMapper sysDictTypeMapper;



    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult page = MybatisPageHelper.findPage(pageRequest, sysDictTypeMapper);
        return page;
    }


}
