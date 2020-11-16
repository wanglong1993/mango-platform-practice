package cn.siques.mango.service;


import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.siques.mangocore.entity.SysDictData;

import java.util.List;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @package:  cn.siques.mangosound.service
 * @description: 字典数据表
 * @author: Shenghao.He
 * @date: Created in 2020-09-03 11:29:19
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
public interface SysDictDataService extends IService<SysDictData> {

    PageResult findPage(PageRequest pageRequest);

    List<SysDictData> listKeyByType(String dictType);
}
