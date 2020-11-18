package cn.siques.mango.service;


import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.siques.mango.entity.SysDictType;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @package:  cn.siques.mangosound.service
 * @description: 字典类型表
 * @author: Shenghao.He
 * @date: Created in 2020-09-03 11:22:09
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
public interface SysDictTypeService extends IService<SysDictType> {

    PageResult findPage(PageRequest pageRequest);

}
