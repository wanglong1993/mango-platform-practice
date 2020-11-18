package cn.siques.mango.dao;

import cn.siques.mango.entity.SysUserRole;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {


    @Delete({
        "delete from sys_user_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);


    @Insert({
        "insert into sys_user_role (id, user_id, ",
        "role_id, create_by, ",
        "create_time, last_update_by, ",
        "last_update_time, remarks)",
        "values (#{id,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, ",
        "#{roleId,jdbcType=BIGINT}, #{createBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{remarks,jdbcType=VARCHAR})"
    })
    int insert(SysUserRole record);



    @Select({
        "select",
        "id, user_id, role_id, create_by, create_time, last_update_by, last_update_time, ",
        "remarks",
        "from sys_user_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_update_by", property="lastUpdateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_update_time", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    SysUserRole selectByPrimaryKey(Long id);

    @Update({
        "update sys_user_role",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "role_id = #{roleId,jdbcType=BIGINT},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "remarks = #{remarks,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUserRole record);



    @Delete({"delete from sys_user_role where user_id=#{id,jdbcType=BIGINT}"})
    int deleteByUserId(Long id);
}