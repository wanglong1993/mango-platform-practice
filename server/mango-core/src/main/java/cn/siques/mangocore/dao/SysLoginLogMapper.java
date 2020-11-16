package cn.siques.mangocore.dao;

import cn.siques.mangocore.entity.SysLoginLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLoginLogMapper {




    @Delete({
        "delete from sys_login_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);


    @Insert({
        "insert into sys_login_log (id, user_name, ",
        "status, ip, create_by, ",
        "create_time, last_update_by, ",
        "last_update_time, remarks)",
        "values (#{id,jdbcType=BIGINT}, #{userName,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{ip,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{remarks,jdbcType=VARCHAR})"
    })
    int insert(SysLoginLog record);


    @Select({
        "select",
        "id, user_name, status, ip, create_by, create_time, last_update_by, last_update_time, ",
        "remarks",
        "from sys_login_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="ip", property="ip", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_update_by", property="lastUpdateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_update_time", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    SysLoginLog selectByPrimaryKey(Long id);


    @Update({
        "update sys_login_log",
        "set user_name = #{userName,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "ip = #{ip,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "remarks = #{remarks,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysLoginLog record);

    @Select({"select * from sys_login_log where user_name = #{userName,jdbcType=VARCHAR}"})
    List<SysLoginLog> findByUserName(String userName);

    @Select({"select * from sys_login_log"})
    List<SysLoginLog> findPage();
}