package cn.siques.mangosound.controller;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.dto.JsonData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import cn.siques.mangosound.entity.SysSoundfile;
import cn.siques.mangosound.service.SysSoundfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @package:  cn.siques.mangosound.controller
 * @description: 
 * @author: Shenghao.He
 * @date: Created in 2020-08-08 17:28:56
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/sys/v1/pri/sysSoundfile")
@Api(description = "SysSoundfileController", tags = {""})
public class SysSoundfileController {

    @Autowired
     SysSoundfileService sysSoundfileService;


    /**
   * 分页查询
   * @param pageRequest
   * @return
   */
    @PostMapping("findPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public JsonData listSysRoleMenu(@RequestBody PageRequest pageRequest) {
        PageResult page = sysSoundfileService.findPage(pageRequest);
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
    public JsonData getSysSoundfile(@PathVariable("id") Integer id){
      return  JsonData.buildSuccess(sysSoundfileService.getById(id));
    }

    /**
     * 新增
     * @param sysSoundfile 
     * @return JsonData
     */
    @PostMapping
    @ApiOperation(value = "新增", notes = "新增")
    public JsonData saveSysSoundfile(@RequestBody SysSoundfile sysSoundfile){
      return  JsonData.buildSuccess(sysSoundfileService.save(sysSoundfile));
    }

    /**
     * 修改
     * @param id id
     * @param sysSoundfile 
     * @return JsonData
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    public JsonData updateSysSoundfile(@PathVariable Integer id, @RequestBody SysSoundfile sysSoundfile){
      return  JsonData.buildSuccess(sysSoundfileService.updateById(sysSoundfile));
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
    public JsonData deleteSysSoundfile(@PathVariable Integer id){
      return  JsonData.buildSuccess(sysSoundfileService.removeById(id));
    }

}
