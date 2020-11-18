package cn.central.dao;


import cn.central.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 这里应该是逻辑删除才对
     * @param id
     * @return
     */
    @Delete({
        "delete from sys_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(long id);

    /**
     * 查找所有
     * @return
     */
    @Select({"select * from sys_menu"})
    List<SysMenu> findAll();

    @Select({"select m.* from sys_menu m, sys_user u, sys_user_role ur, sys_role_menu rm\n" +
            "  \twhere u.name = #{name,jdbcType=VARCHAR} and u.id = ur.user_id \n" +
            "  \tand ur.role_id = rm.role_id and rm.menu_id = m.id"})
    List<SysMenu> findByUserName(String name);

    @Select({"select m.* from sys_menu m, sys_role_menu rm\n" +
            "    where rm.role_id = #{roleId,jdbcType=BIGINT}\n" +
            "    and m.id = rm.menu_id"})
    List<SysMenu> findRoleMenus(Long roleId);


    @Delete({"DELETE FROM sys_menu WHERE parent_ids LIKE  CONCAT('%',#{id,jdbcType=VARCHAR},'%')"})
    int deleteChild(Long id);
}