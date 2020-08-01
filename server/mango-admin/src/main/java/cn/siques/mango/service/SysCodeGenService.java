package cn.siques.mango.service;

import cn.hutool.db.Entity;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import cn.siques.mangocore.entity.gen.GenConfig;
import cn.siques.mangocore.service.CrudService;

import java.util.List;

public interface SysCodeGenService  {
   byte[] generatorCode(GenConfig genConfig);

    PageResult findPage(PageRequest pageRequest);


}
