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
@TableName("sys_log")
@ApiModel(description = "日志表")
public class SysLog extends Model<SysLog> {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String userName;

    private String operation;

    private String method;


    private String params;


    private Long time;


    private String ip;

    private String createBy;


    private Date createTime;


    private String lastUpdateBy;


    private Date lastUpdateTime;

}