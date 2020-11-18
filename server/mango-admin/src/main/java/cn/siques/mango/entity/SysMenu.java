package cn.siques.mango.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(description = "菜单表")
public class SysMenu extends Model<SysMenu> implements Serializable   {
    private static final long serialVersionUID = -4855350263474682807L;

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;
    // 非数据库字段
    private List<SysMenu> children;


    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;




    @ApiModelProperty(value = "父菜单树")
    private String parentIds;


    private String url;

    private String perms;


    private Integer type;


    private String icon;


    private Integer orderNum;


    private String createBy;


    private Date createTime;


    private String lastUpdateBy;


    private Date lastUpdateTime;


    private Byte delFlag;

}