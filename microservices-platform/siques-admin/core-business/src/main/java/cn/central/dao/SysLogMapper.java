package cn.central.dao;


import cn.central.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogMapper extends BaseMapper<SysLog> {

    @Select({"select * from sys_log"})
    List<SysLog> findPage();

}