package cn.central.dao;


import cn.central.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {



    @Select({"select * from sys_role"})
    List<SysRole> findAll();

    @Select({"select * from sys_role where name = #{name}"})
    List<SysRole> findByName(String name);
}