package cn.central.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_role_menu")
@ApiModel(description = "角色菜单表")
public class SysRoleMenu extends Model<SysRoleMenu> {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long menuId;


    private String createBy;


    private Date createTime;


    private String lastUpdateBy;


    private Date lastUpdateTime;
}