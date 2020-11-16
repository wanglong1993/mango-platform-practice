package cn.siques.mango.controller.dto;

import cn.siques.mangocore.entity.SysRoleMenu;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author : heshenghao
 * @date : 16:52 2020/11/16
 */
@Data
public class RoleMenuDto {


    private Long roleId;
    private List<SysRoleMenu> roleMenus;

}
