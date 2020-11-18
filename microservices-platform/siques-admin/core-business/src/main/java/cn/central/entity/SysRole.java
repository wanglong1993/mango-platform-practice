package cn.central.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@TableName("sys_role")
@ApiModel(description = "角色表")
public class SysRole  extends Model<SysRole> {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private Long id;

    private String name;

    @TableField(exist = false)
    private  List<SysMenu> sysMenuList;

    private String remark;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale ="zh",timezone = "GMT+8")
    private Date createTime;

    private String lastUpdateBy;


    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale ="zh",timezone = "GMT+8")
    private Date lastUpdateTime;


    private Byte delFlag;

}