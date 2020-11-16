package cn.siques.mangocore.dao;

import cn.siques.mangocore.entity.SysLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface SysLogMapper {


    @Delete({
        "delete from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_log (id, user_name, ",
        "operation, method, ",
        "params, time, ip)",
        "values (#{id,jdbcType=BIGINT}, #{userName,jdbcType=VARCHAR}, ",
        "#{operation,jdbcType=VARCHAR}, #{method,jdbcType=VARCHAR}, ",
        "#{params,jdbcType=VARCHAR}, #{time,jdbcType=BIGINT}, #{ip,jdbcType=VARCHAR})"
    })
    int insert(SysLog record);




    @Select({
        "select",
        "id, user_name, operation, method, params, time, ip",
        "from sys_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="operation", property="operation", jdbcType=JdbcType.VARCHAR),
        @Result(column="method", property="method", jdbcType=JdbcType.VARCHAR),
        @Result(column="params", property="params", jdbcType=JdbcType.VARCHAR),
        @Result(column="time", property="time", jdbcType=JdbcType.BIGINT),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR)
    })
    SysLog selectByPrimaryKey(Long id);


    @Update({
        "update sys_log",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "operation = #{operation,jdbcType=VARCHAR},",
          "method = #{method,jdbcType=VARCHAR},",
          "params = #{params,jdbcType=VARCHAR},",
          "time = #{time,jdbcType=BIGINT},",
          "ip = #{ip,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysLog record);


    @Select({"select * from sys_log"})
    List<SysLog> findPage();

}