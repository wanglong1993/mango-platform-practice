package cn.siques.mango.dao;

import cn.siques.mango.entity.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictMapper  extends BaseMapper<SysDict> {


    /**
     * 分页查询
     * @return
     */
    @Select({"select * from sys_dict"})
    List<SysDict> findPage();

    /**
     * 根据标签名称查询
     * @param label
     * @return
     */

    @Select({"select * from sys_dict where label=#{label,jdbcType=VARCHAR}"})
    List<SysDict> findByLabel(@Param(value = "label") String label);


    @Select({"select * from sys_dict where label like  concat('%',#{label},'%')"})
    List<SysDict> findPageByLabel(@Param(value = "label")String label);

}