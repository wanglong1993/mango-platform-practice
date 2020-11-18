package cn.siques.mango.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dept")
@ApiModel(description = "部门表")
public class SysDept  extends Model<SysDept> {

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private Long id;


    @TableField(exist = false)
    private String parentName;

    @TableField(exist = false)
    private Integer level;


    @TableField(exist = false)
    private List<SysDept> children;


    @TableField(exist = false)
    private List<Long>  deptList;
    // 数据库字段


    private  String parentIds;

    public String getParentIds() {
        return parentIds;
    }

    // 设置list
    public void setParentIds(String parentIds) {
        Long id = this.getId();
        String[] split = parentIds.split(",");
        List<Long> list = new ArrayList<>();
        for (String s:split
             ) {
            list.add(Long.valueOf(s));
        }
        list.add(id);
        this.deptList=list;
        this.parentIds = parentIds;
    }


    private Long parentId;

    private String name;

    private Integer orderNum;


    private String createBy;


    private Date createTime;


    private String lastUpdateBy;


    private Date lastUpdateTime;


    private Byte delFlag;



}