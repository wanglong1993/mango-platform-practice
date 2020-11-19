package cn.central.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@TableName("sys_user")
@ApiModel(description = "用户表")
public class SysUser  extends Model<SysUser> {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private Long id;

    // 非数据库字段
    @TableField(exist = false)
    private String deptName;
    // 非数据库字段
    @TableField(exist = false)
    private String roleNames;
    // 非数据库字段
    @TableField(exist = false)
    private List<SysUserRole> userRoles = new ArrayList<>();


    private String name;

    private Integer orderNum;

    private String nickName;


    private String deptTree;

    private String avatar;


//    @JsonIgnore
    private String password;


    @JsonIgnore
    private String salt;


    private String email;


    private String mobile;


    private Byte status;

    private Long deptId;

    private String createBy;


    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;


    private String lastUpdateBy;



    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastUpdateTime;


    private Byte delFlag;


}