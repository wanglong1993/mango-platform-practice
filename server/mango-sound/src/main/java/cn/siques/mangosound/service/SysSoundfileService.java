package cn.siques.mangosound.service;



import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import com.aliyun.oss.model.OSSObject;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.siques.mangosound.entity.SysSoundfile;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @package:  cn.siques.mangosound.service
 * @description: 
 * @author: Shenghao.He
 * @date: Created in 2020-08-08 17:28:56
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
public interface SysSoundfileService extends IService<SysSoundfile> {

    PageResult findPage(PageRequest pageRequest);

    OSSObject getIsFromOss(Integer id);

    PageResult searchSoundfile(SysSoundfile sysSoundfile, PageRequest pageRequest);
}
