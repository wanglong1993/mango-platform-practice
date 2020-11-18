package cn.siques.mango.service;

import cn.siques.mango.controller.dto.DbConfig;
import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.entity.gen.GenConfig;

import java.util.List;

public interface SysCodeGenService  {
   byte[] generatorCode(GenConfig genConfig);

    PageResult findPage(PageRequest pageRequest);


    List<DbConfig> queryDbList();

}
