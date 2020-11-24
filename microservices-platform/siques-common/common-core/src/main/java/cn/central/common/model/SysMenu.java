package cn.central.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("sys_menu")
@ApiModel(description = "菜单或者权限表")
public class SysMenu extends SuperEntity<SysMenu>  {
    private static final long serialVersionUID = -4855350263474682807L;


    // 非数据库字段
    @TableField(exist = false)
    private String parentName;
    // 非数据库字段
    @TableField(exist = false)
    private Integer level;
    // 非数据库字段
    @TableField(exist = false)
    private List<SysMenu> children;


    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;


    @ApiModelProperty(value = "父菜单集合")
    private String parentIds;


    private String url;

    private String perms;


    private Integer type;


    private String icon;


    private Integer orderNum;



    private Byte delFlag;

}