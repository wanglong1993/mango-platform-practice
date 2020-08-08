package cn.siques.mangosound.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @package:  cn.siques.mangosound.entity
 * @description: 
 * @author: Shenghao.He
 * @date: Created in 2020-08-08 17:28:56
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@Data
@TableName("sys_soundfile")
@ApiModel(description = "")
@EqualsAndHashCode(callSuper = true)
public class SysSoundfile extends Model<SysSoundfile> {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Integer id;
    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名")
    private String name;
    /**
     * 扩展名
     */
    @ApiModelProperty(value = "扩展名")
    private String ext;
    /**
     * 大小
     */
    @ApiModelProperty(value = "大小")
    private String size;
    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    private String path;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String classification;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
    /**
     * 原始链接
     */
    @ApiModelProperty(value = "原始链接")
    private String url;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer statu;
    /**
     * 地图位置
     */
    @ApiModelProperty(value = "地图位置")
    private String location;
    /**
     * 封面图
     */
    @ApiModelProperty(value = "封面图")
    private String cover;

}
