package cn.siques.mango.service;


import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysDeptService extends IService<SysDept> {

    List<SysDept> findDeptTree();


    PageResult findPageById(PageRequest pageRequest, Long id);

    PageResult findPage(PageRequest pageRequest);
}
