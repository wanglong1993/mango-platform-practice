package cn.siques.mango.controller.dto;

import cn.siques.mango.entity.SysRoleMenu;
import lombok.Data;

import java.util.List;

/**
 * @author : heshenghao
 * @date : 16:52 2020/11/16
 */
@Data
public class RoleMenuDto {


    private Long roleId;
    private List<SysRoleMenu> roleMenus;

}
