package cn.siques.mango.service;


import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocore.entity.SysDept;
import cn.siques.mangocore.service.CrudService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysDeptService extends IService<SysDept> {

    List<SysDept> findDeptTree();


    PageResult findPageById(PageRequest pageRequest, Long id);

    PageResult findPage(PageRequest pageRequest);
}
