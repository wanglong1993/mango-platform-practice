package cn.central.oauth.dao;


import cn.central.oauth.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 查找用户
     * @param username
     * @return
     * 这种查询方式不好 ⭐⭐
     */
    @Select({"select * from sys_user where name = #{username}"})
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="id",property="menus",
                    many=@Many(
                            select="cn.central.oauth.dao.SysMenuDao.findMenusByUid",
                            fetchType= FetchType.LAZY
                    )
            )
    })
    Optional<SysUser> findUserByName(@Param("username") String username);



}