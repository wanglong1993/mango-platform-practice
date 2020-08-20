package cn.siques.mango.service;

import cn.hutool.db.Entity;

import cn.siques.mango.controller.dto.DbConfig;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocore.entity.gen.GenConfig;
import cn.siques.mangocore.service.CrudService;

import java.util.List;

public interface SysCodeGenService  {
   byte[] generatorCode(GenConfig genConfig);

    PageResult findPage(PageRequest pageRequest);


    List<DbConfig> queryDbList();

}
