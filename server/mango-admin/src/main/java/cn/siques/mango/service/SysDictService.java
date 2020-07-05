package cn.siques.mango.service;

import cn.siques.mangocore.entity.SysDict;
import cn.siques.mangocore.service.CrudService;

import java.util.List;

public interface SysDictService extends CrudService<SysDict> {

    /**
     * 根据名称查询
     * @param label
     * @return
     */
        List<SysDict> findByLabel(String label);

}
