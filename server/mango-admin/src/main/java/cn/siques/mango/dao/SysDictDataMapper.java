package cn.siques.mango.dao;


import cn.siques.mango.entity.SysDictData;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @package:  cn.siques.mangosound.mapper
 * @description: 字典数据表
 * @author: Shenghao.He
 * @date: Created in 2020-09-03 11:29:19
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@Component
public interface SysDictDataMapper extends BaseMapper<SysDictData> {

    /**
   * 分页查询
   * @return
   */
    @Select({"select * from sys_dict_data"})
    List<SysDictData> findPage();
}
