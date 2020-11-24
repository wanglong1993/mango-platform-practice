package cn.central.common.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author zlt
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(description = "用户表")
public class SysUser extends SuperEntity<SysUser> {
	private static final long serialVersionUID = -5886012896705137070L;


    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "登录账号")
    private String loginCode;

    @ApiModelProperty(value = "登录密码")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String userName;

    @ApiModelProperty(value = "头像路径")
    private String avatar;

    @JsonIgnore
    private String salt;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "用户状态")
    private Byte status;


    @ApiModelProperty(value = "排序")
    private Integer orderNum;


    @TableField(exist = false)
    private List<SysRole> sysRoles;

	@TableField(exist = false)
	private SysRole roleId;


//	@TableField(exist = false)
//    private String oldPassword;
//    @TableField(exist = false)
//	private String newPassword;
}
