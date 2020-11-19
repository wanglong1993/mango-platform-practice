package cn.central.service;



import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysDeptService extends IService<SysDept> {

    List<SysDept> findDeptTree();


    PageResult findPageById(PageRequest pageRequest, Long id);

    PageResult findPage(PageRequest pageRequest);
}
