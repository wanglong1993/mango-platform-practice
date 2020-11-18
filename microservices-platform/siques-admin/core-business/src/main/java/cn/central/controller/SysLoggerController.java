package cn.central.controller;

import org.springframework.beans.factory.annotation.Autowired;
import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.common.dto.JsonData;
import cn.central.entity.SysLogger;
import cn.central.service.SysLoggerService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * <p>
 * 
 * </p>
 *
 * @package:   cn.central.controller
 * @description: 
 * @author: Shenghao.He
 * @date: Created in 2020-11-18 22:12:02
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/sys/v1/pri/sysLogger")
@Api(description = "SysLoggerController", tags = {"日志接口"})
public class SysLoggerController {

    @Autowired
    SysLoggerService sysLoggerService;


    /**
   * 分页查询
   * @param pageRequest
   * @return
   */
    @PostMapping("findPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PreAuthorize("hasAuthority('sys:logger:view')")
    public JsonData listSysRoleMenu(@RequestBody PageRequest pageRequest) {
        PageResult page = sysLoggerService.findPage(pageRequest);
        return JsonData.buildSuccess(page);
    }


    /**
     * 通过id查询
     * @param id id
     * @return JsonData
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    @PreAuthorize("hasAuthority('sys:logger:view')")
    public JsonData getSysLogger(@PathVariable("id") Integer id){
      return  JsonData.buildSuccess(sysLoggerService.getById(id));
    }

    /**
     * 新增
     * @param sysLogger 
     * @return JsonData
     */
    @PostMapping
    @ApiOperation(value = "新增", notes = "新增")
    @PreAuthorize("hasAuthority('sys:logger:add')")
    public JsonData saveSysLogger(@RequestBody SysLogger sysLogger){
      return  JsonData.buildSuccess(sysLoggerService.save(sysLogger));
    }

    /**
     * 更新
     * @param id id
     * @param sysLogger 
     * @return JsonData
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    @PreAuthorize("hasAuthority('sys:logger:edit')")
    public JsonData updateSysLogger(@PathVariable Integer id, @RequestBody SysLogger sysLogger){
      return  JsonData.buildSuccess(sysLoggerService.updateById(sysLogger));
    }

    /**
     * 通过id删除
     * @param id id
     * @return JsonData
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    @PreAuthorize("hasAuthority('sys:logger:delete')")
    public JsonData deleteSysLogger(@PathVariable Integer id){
      return  JsonData.buildSuccess(sysLoggerService.removeById(id));
    }

}
