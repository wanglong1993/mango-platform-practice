package cn.siques.mango.service;

import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.mango.entity.SysDict;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysDictService extends IService<SysDict> {

    /**
     * 根据名称查询
     * @param label
     * @return
     */
        List<SysDict> findByLabel(String label);

    PageResult findPage(PageRequest pageRequest);

}
