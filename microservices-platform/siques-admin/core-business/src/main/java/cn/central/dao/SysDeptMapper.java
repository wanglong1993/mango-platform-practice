package cn.central.dao;


import cn.central.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {

     // TODO
    @Select({"select * from sys_dept"})
    List<SysDept> findAll();

    @Select({"select * from sys_dept"})
    List<SysDept> findPage();

    @Select({"SELECT d.* FROM sys_dept d " +
            " WHERE  d.parent_ids LIKE CONCAT('%',#{id,jdbcType=BIGINT},'%') OR d.id=#{id,jdbcType=BIGINT}"})
    List<SysDept> findPageById(Long id);
 }