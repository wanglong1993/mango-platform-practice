package cn.siques.mango.dao;

import cn.siques.mango.entity.*;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper  extends BaseMapper<SysUser> {




    @Delete({
        "delete from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);


    @Insert({
        "insert into sys_user (id, name, ",
        "nick_name, avatar, ",
        "password, salt, ",
        "email, mobile, status, ",
        "dept_id, create_by, ",
        "create_time, last_update_by, ",
        "last_update_time, del_flag,order_num,dept_tree)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{nickName,jdbcType=VARCHAR}, #{avatar,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR}, #{status,jdbcType=TINYINT}, ",
        "#{deptId,jdbcType=BIGINT}, #{createBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=TINYINT},#{orderNum,jdbcType=INTEGER},#{deptTree,jdbcType=VARCHAR})"
    })
    int insert(SysUser record);



    @Select({
        "select",
        "id, name, nick_name, avatar, password, salt, email, mobile, status, dept_id, ",
        "create_by, create_time, last_update_by, last_update_time, del_flag,order_num",
        "from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results(id = "userMap",value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="last_update_by", property="lastUpdateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_update_time", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
            @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER)
    })
    SysUser selectByPrimaryKey(Long id);



    @Update({
        "update sys_user",
        "set name = #{name,jdbcType=VARCHAR},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "avatar = #{avatar,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "salt = #{salt,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=TINYINT},",
          "dept_id = #{deptId,jdbcType=BIGINT},",
            "order_num = #{order_num,jdbcType=INTEGER},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "last_update_by = #{lastUpdateBy,jdbcType=VARCHAR},",
          "last_update_time = #{lastUpdateTime,jdbcType=TIMESTAMP},",
          "del_flag = #{delFlag,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUser record);

    @Select({"select * from sys_user"})
    List<SysUser> findAll();

    /**
     * 分页查询
     * @return
     */
    @Select({"select * from sys_user"})
    List<SysUser> findPage();

    @Select({"SELECT * FROM sys_user u\n" +
            "LEFT JOIN sys_dept d ON u.dept_id= d.id order by u.order_num DESC"})
    @Results(id = "deptUser",value = {
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
            @Result(column="avatar", property="avatar", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="salt", property="salt", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
            @Result(column="dept_id", property="deptId", jdbcType=JdbcType.BIGINT),
            @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="last_update_by", property="lastUpdateBy", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_update_time", property="lastUpdateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.TINYINT),
            @Result(column="order_num", property="orderNum", jdbcType=JdbcType.INTEGER),
            @Result(property = "sysDept" , column = "dept_id" ,
                    one = @One(select = "cn.siques.mangocore.dao.SysDeptMapper.selectById"))
    })
    List<SysDeptUser> findPageWithDept();

    /**
     * 查找用户
     * @param username
     * @return
     */
    @Select({"select * from sys_user where name = #{username}"})
    SysUser findByName(String username);

    /**
     * 查找用户权限
     * @param name
     * @return
     */
    @Select({"select * from sys"})
    Set<String> findPermission(String name);


    @Select({"SELECT r.* FROM  sys_user u ,sys_user_role ur, sys_role r \n" +
            "WHERE u.id = #{id,jdbcType=BIGINT} AND u.id= ur.`user_id` AND ur.`role_id` = r.id"})
    List<SysRole> findUserRolesById(long id);


    @Select({"SELECT u.*,d.* FROM sys_user u,sys_dept d\n" +
            "WHERE  d.id=#{id,jdbcType=BIGINT} AND  u.dept_tree LIKE CONCAT('%',d.id,'%')"})
    @ResultMap("deptUser")
    List<SysDeptUser> findUsersByDeptId(Long id);


    @Select({"select u.* from  sys_user u ,sys_user_role ur where ur.`role_id`=#{id,jdbcType=BIGINT} and ur.`user_id`=u.`id`\n"})
    List<SysUser> findUsersByRoleId(Long id);
}