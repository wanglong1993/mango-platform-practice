package cn.siques.mango.service;


import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocore.entity.SysDept;
import cn.siques.mangocore.service.CrudService;

import java.util.List;


public interface SysDeptService extends CrudService<SysDept> {

    List<SysDept> findDeptTree();


    PageResult findPageById(PageRequest pageRequest, Long id);
}
