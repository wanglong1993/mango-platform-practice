package cn.siques.mangosound.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.siques.mangosound.entity.SysSoundfile;

/**
 * <p>
 * 
 * </p>
 *
 * @package:  cn.siques.mangosound.mapper
 * @description: 
 * @author: Shenghao.He
 * @date: Created in 2020-08-08 17:28:56
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@Component
public interface SysSoundfileMapper extends BaseMapper<SysSoundfile> {

    /**
   * 分页查询
   * @return
   */
    @Select({"select * from sys_soundfile"})
    List<SysSoundfile> findPage();

    @Select({"<script> SELECT * FROM sys_soundfile" +
            "<where>" +
            "<if test='name != null' >" +
            " name like concat('%',#{name},'%')" +
            "</if>" +
            "<if test='classification != null' >" +
            "or classification like concat('%',#{classification},'%')" +
            "</if>" +
            "</where>"+
            " </script>" })
    List<SysSoundfile> searchSoundfile(HashMap<String, String> map);

    List<SysSoundfile> findAll();
}
