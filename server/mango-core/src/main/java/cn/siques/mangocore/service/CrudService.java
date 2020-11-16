package cn.siques.mangocore.service;



import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;

import java.util.List;

public interface CrudService<T> {
    /**
     * 保存操作
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 删除操作
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 批量删除
     * @param records
     * @return
     */
    int  delete(List<T> records);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 封装参数 解耦
     * @param pageRequest
     * @return
     */
    PageResult findPage(PageRequest pageRequest);
}
