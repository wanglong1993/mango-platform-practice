package cn.central.dao;

import cn.central.entity.SysDeptUser;
import cn.central.entity.SysRole;
import cn.central.entity.SysUser;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SysUserMapper  extends BaseMapper<SysUser> {



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
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="nick_name", property="nickName", jdbcType= JdbcType.VARCHAR),
            @Result(column="avatar", property="avatar", jdbcType= JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType= JdbcType.VARCHAR),
            @Result(column="salt", property="salt", jdbcType= JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
            @Result(column="mobile", property="mobile", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.TINYINT),
            @Result(column="dept_id", property="deptId", jdbcType= JdbcType.BIGINT),
            @Result(column="create_by", property="createBy", jdbcType= JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="last_update_by", property="lastUpdateBy", jdbcType= JdbcType.VARCHAR),
            @Result(column="last_update_time", property="lastUpdateTime", jdbcType= JdbcType.TIMESTAMP),
            @Result(column="del_flag", property="delFlag", jdbcType= JdbcType.TINYINT),
            @Result(column="order_num", property="orderNum", jdbcType= JdbcType.INTEGER),
            @Result(property = "sysDept" , column = "dept_id" ,
                    one = @One(select = "cn.central.dao.SysDeptMapper.selectById"))
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