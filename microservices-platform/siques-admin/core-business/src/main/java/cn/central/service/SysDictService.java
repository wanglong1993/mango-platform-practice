package cn.central.service;


import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.entity.SysDict;
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
