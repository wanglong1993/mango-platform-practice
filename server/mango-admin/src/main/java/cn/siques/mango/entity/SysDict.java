package cn.siques.mango.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_dict")
@ApiModel(description = "字典表")
public class SysDict extends Model<SysDict> {

    @TableId
    @ApiModelProperty(value = "编号")
    private Long id;

    private String value;

    private String label;


    private String type;

    private String description;


    private Long sort;

    private String remarks;


    private String createBy;


    private Date createTime;


    private String lastUpdateBy;


    private Date lastUpdateTime;


    private Byte delFlag;


}