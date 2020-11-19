package cn.central.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_login_log")
@ApiModel(description = "登录日志表")
public class SysLoginLog  extends Model<SysLoginLog> {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private Long id;

    public static final String STATUS_LOGIN = "login";
    public static final String STATUS_LOGOUT = "logout";
    public static final String STATUS_ONLINE = "online";

    @ApiModelProperty(value = "用户名")
    private String userName;


    private String status;


    private String ip;

    private String createBy;


    private Date createTime;


    private String lastUpdateBy;


    private Date lastUpdateTime;


    private String remarks;


}