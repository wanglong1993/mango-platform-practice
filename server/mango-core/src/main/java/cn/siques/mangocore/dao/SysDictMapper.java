package cn.siques.mangocore.dao;

import cn.siques.mangocore.entity.SysDict;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictMapper {

    @Delete({
        "delete from sys_dict",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long key);


    @Insert({
        "insert into sys_dict (id, value, ",
        "label, type, description, ",
        "sort, remarks, create_by, ",
        "create_time, last_update_by, ",
        "last_update_time, del_flag)",
        "values (#{id,jdbcType=BIGINT}, #{value,jdbcType=VARCHAR}, ",
        "#{label,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{sort,jdbcType=DECIMAL}, #{remarks,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=TINYINT})"
    })
    int insert(SysDict record);




    @Select({
        "select",
        "id, value, label, type, description, sort, remarks, create_by, create_time, ",
        "last_update_by, last_update_time, del_flag",
        "from sys_dict",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="label", property="label", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.DECIMAL),
        @Result(column="remarks", property="remarks", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_update_by", property="lastUpdateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_update_time", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT)
    })
    SysDict selectByPrimaryKey(Long key);


    @Update({
        "update sys_dict",
        "set value = #{value,jdbcType=VARCHAR},",
          "label = #{label,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=DECIMAL},",
          "remarks = #{remarks,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "del_flag = #{delFlag,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysDict record);


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