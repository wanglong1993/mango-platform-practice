package cn.siques.mango.dao;

import cn.siques.mango.entity.SysLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {


    @Select({"select * from sys_login_log where user_name = #{userName,jdbcType=VARCHAR}"})
    List<SysLoginLog> findByUserName(String userName);

    @Select({"select * from sys_login_log"})
    List<SysLoginLog> findPage();
}