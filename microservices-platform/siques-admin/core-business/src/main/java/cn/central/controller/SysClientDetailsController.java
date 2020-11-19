package cn.central.controller;

import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.common.dto.JsonData;

import cn.central.entity.SysClientDetails;
import cn.central.service.SysClientDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 
 * </p>
 *
 * @package:  cn.siques.mangosound.controller
 * @description: 
 * @author: Shenghao.He
 * @date: Created in 2020-11-18 22:50:20
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/sys/v1/pri/sysClientDetails")
@Api(description = "SysClientDetailsController", tags = {"客户端接口"})
public class SysClientDetailsController {

    @Autowired
    SysClientDetailsService sysClientDetailsService;


    /**
   * 分页查询
   * @param pageRequest
   * @return
   */
    @PostMapping("findPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public JsonData listSysRoleMenu(@RequestBody PageRequest pageRequest) {
        return JsonData.buildSuccess(sysClientDetailsService.findPage(pageRequest));
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
    public JsonData getSysClientDetails(@PathVariable("id") Long id){
      return  JsonData.buildSuccess(sysClientDetailsService.getById(id));
    }

    /**
     * 新增
     * @param sysClientDetails 
     * @return JsonData
     */
    @PostMapping
    @ApiOperation(value = "新增", notes = "新增")
    public JsonData saveSysClientDetails(@RequestBody SysClientDetails sysClientDetails){
      return  JsonData.buildSuccess(sysClientDetailsService.save(sysClientDetails));
    }

    /**
     * 修改
     * @param id id
     * @param sysClientDetails 
     * @return JsonData
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    public JsonData updateSysClientDetails(@PathVariable Long id, @RequestBody SysClientDetails sysClientDetails){
      return  JsonData.buildSuccess(sysClientDetailsService.updateById(sysClientDetails));
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
    public JsonData deleteSysClientDetails(@PathVariable Long id){
      return  JsonData.buildSuccess(sysClientDetailsService.removeById(id));
    }

}
