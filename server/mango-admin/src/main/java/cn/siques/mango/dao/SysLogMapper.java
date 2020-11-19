package cn.siques.mango.dao;

import cn.siques.mango.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SysLogMapper extends BaseMapper<SysLog> {

    @Select({"select * from sys_log"})
    List<SysLog> findPage();

}