package cn.central.controller;


import cn.central.common.Page.PageRequest;
import cn.central.common.Page.PageResult;
import cn.central.common.dto.JsonData;
import cn.central.common.utils.SecurityUtils;
import cn.central.entity.SysDictData;
import cn.central.entity.SysDictType;
import cn.central.service.SysDictDataService;
import cn.central.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @package:  cn.siques.mangosound.controller
 * @description: 字典类型表
 * @author: Shenghao.He
 * @date: Created in 2020-09-03 11:22:09
 * @copyright: Copyright (c) 2020
 * @version: V1.0
 * @modified: Shenghao.He
 */
@RestController
@RequestMapping("/api/sys/v1/pri/sysDictType")
@Api(description = "SysDictTypeController", tags = {"字典类型接口"})
public class SysDictTypeController {

    @Autowired
    SysDictTypeService sysDictTypeService;

    @Autowired
    SysDictDataService sysDictDataService;

    /**
   * 分页查询字典类型表
   * @param pageRequest
   * @return
   */
    @PostMapping("findPage")
    @ApiOperation(value = "分页查询字典类型表", notes = "分页查询字典类型表")
    public JsonData listSysRoleMenu(@RequestBody PageRequest pageRequest) {
        PageResult page = sysDictTypeService.findPage(pageRequest);
        return JsonData.buildSuccess(page);
    }

    @PostMapping("findKeyByType")
    @ApiOperation(value = "根据字典类型查字典树", notes = "根据字典型查字典树")
    public JsonData listKeyByType(@RequestBody SysDictType sysDictType) {
//        System.out.println(sysDictType);
//        QueryWrapper<SysDictData> wrapper = new QueryWrapper<>();
//        wrapper.eq("dict_type",sysDictType.getDictType());
//
//        List<SysDictData> list = sysDictDataService.list(wrapper);

        List<SysDictData> list =  sysDictDataService.listKeyByType(sysDictType.getDictType());

        return JsonData.buildSuccess(list);
    }

    /**
     * 通过id查询字典类型表
     * @param id id
     * @return JsonData
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "通过id查询字典类型表", notes = "通过id查询字典类型表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    public JsonData getSysDictType(@PathVariable("id") String id){
      return  JsonData.buildSuccess(sysDictTypeService.getById(id));
    }

    /**
     * 新增字典类型表
     * @param sysDictType 字典类型表
     * @return JsonData
     */
    @PreAuthorize("hasAuthority('sys:dict:add')")
    @PostMapping
    @ApiOperation(value = "新增字典类型表", notes = "新增字典类型表")
    public JsonData saveSysDictType(@RequestBody SysDictType sysDictType){
                sysDictType.setCreateBy(SecurityUtils.getUsername());
      return  JsonData.buildSuccess(sysDictTypeService.save(sysDictType));
    }

    /**
     * 修改字典类型表
     * @param id id
     * @param sysDictType 字典类型表
     * @return JsonData
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改字典类型表", notes = "修改字典类型表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    public JsonData updateSysDictType(@PathVariable String id, @RequestBody SysDictType sysDictType){
      return  JsonData.buildSuccess(sysDictTypeService.updateById(sysDictType));
    }

    /**
     * 通过id删除字典类型表
     * @param id id
     * @return JsonData
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除字典类型表", notes = "删除字典类型表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    public JsonData deleteSysDictType(@PathVariable String id){
      return  JsonData.buildSuccess(sysDictTypeService.removeById(id));
    }

}
