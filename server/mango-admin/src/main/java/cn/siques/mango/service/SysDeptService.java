package cn.siques.mango.service;

import cn.siques.mangocore.entity.SysDept;
import cn.siques.mangocore.service.CrudService;

import java.util.List;


public interface SysDeptService extends CrudService<SysDept> {

    List<SysDept> findDeptTree();
}
