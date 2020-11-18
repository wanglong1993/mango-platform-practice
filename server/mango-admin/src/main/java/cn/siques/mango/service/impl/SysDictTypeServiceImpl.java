package cn.siques.mango.service.impl;

import cn.siques.mango.service.SysDictTypeService;
import cn.siques.Page.MybatisPageHelper;
import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.dao.SysDictTypeMapper;
import cn.siques.mango.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
