package cn.siques.mangocore.dao;


import cn.siques.mangocore.entity.SysConfig;
import cn.siques.mangocore.entity.SysDictData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

public interface SysConfigMapper  extends BaseMapper<SysConfig> {


    @Insert({
        "insert into sys_config (id, value, ",
        "label, type, description, ",
        "sort, create_by, ",
        "create_time, last_update_by, ",
        "last_update_time, del_flag, ",
        "remarks)",
        "values (#{id,jdbcType=BIGINT}, #{value,jdbcType=VARCHAR}, ",
        "#{label,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{sort,jdbcType=DECIMAL}, #{createBy,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{lastUpdateBy,jdbcType=VARCHAR}, ",
        "#{lastUpdateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=TINYINT}, ",
        "#{remarks,jdbcType=VARBINARY})"
    })
    int insert(SysConfig record);

}