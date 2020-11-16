package cn.siques.mangooauth.dao;

import cn.siques.mangooauth.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 查找用户
     * @param username
     * @return
     */
    @Select({"select * from sys_user where name = #{username}"})
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="name",property="name"),
            @Result(column="id",property="menus",
                    many=@Many(
                            select="cn.siques.mangooauth.dao.SysMenuDao.findMenusByUid",
                            fetchType= FetchType.LAZY
                    )
            )
    })
    Optional<SysUser> findUserByName(@Param("username") String username);



}