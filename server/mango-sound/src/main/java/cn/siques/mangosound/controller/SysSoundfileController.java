package cn.siques.mangosound.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.dto.JsonData;

import cn.siques.mangosound.entity.dto.SearchPage;
import cn.siques.mangosound.mapper.SysSoundfileMapper;
import com.aliyun.oss.model.OSSObject;

import cn.siques.mangosound.entity.SysSoundfile;
import cn.siques.mangosound.service.SysSoundfileService;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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
@RequestMapping("/api/sys/v1/pri/sysSoundfile")
@Api(description = "声音文件控制器", tags = {""})
public class SysSoundfileController {

    @Autowired
     SysSoundfileService sysSoundfileService;


//
//    @PostMapping("findPage")
//    @PreAuthorize("hasAuthority('sys:sound:view')")
//    @ApiOperation(value = "分页查询", notes = "分页查询")
//    public JsonData listSysSoundPage(@RequestBody PageRequest pageRequest) {
//        PageResult page =
//        return JsonData.buildSuccess(page);
//    }


    /**
     * 分页查询
     * @param searchPage
     * @return
     */
    @PostMapping("/findPage")
    @PreAuthorize("hasAuthority('sys:sound:view')")
    @ApiOperation(value = "分页查询及搜索", notes = "分页查询及搜索")
    public JsonData searchSysSoundfile(@RequestBody SearchPage searchPage){
        if(ObjectUtil.isNull(searchPage.getSysSoundfile())) return JsonData.buildSuccess(sysSoundfileService.findPage(searchPage.getPageRequest()));
        else   return  JsonData.buildSuccess(sysSoundfileService.searchSoundfile(searchPage.getSysSoundfile(),searchPage.getPageRequest()));

    }



    /**
     * 查询全部
     * @return
     */
    @GetMapping("findAll")
    @PreAuthorize("hasAuthority('sys:sound:view')")
    @ApiOperation(value = "查询所有", notes = "查询所有")
    @Cacheable("findAllSoundFile")
    public JsonData listSysSoundFiles() {
        List<SysSoundfile> list = sysSoundfileService.list();
        return JsonData.buildSuccess(list);
    }


    /**
     * 通过id查询
     * @param id id
     * @return JsonData
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:sound:view')")
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
    @PreAuthorize("hasAuthority('sys:sound:add')")
    @ApiOperation(value = "新增", notes = "新增")
    @CacheEvict(cacheNames = "findAllSoundFile",allEntries = true)
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
    @PreAuthorize("hasAuthority('sys:sound:edit')")
    @ApiOperation(value = "修改", notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    @CacheEvict(cacheNames = "findAllSoundFile",allEntries = true)
    public JsonData updateSysSoundfile(@PathVariable Integer id, @RequestBody SysSoundfile sysSoundfile){

      return  JsonData.buildSuccess(sysSoundfileService.updateById(sysSoundfile));
    }

    /**
     * 通过id删除
     * @param id id
     * @return JsonData
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:sound:edit')")
    @ApiOperation(value = "删除", notes = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    @CacheEvict(cacheNames = "findAllSoundFile",allEntries = true)
    public JsonData deleteSysSoundfile(@PathVariable Integer id){
      return  JsonData.buildSuccess(sysSoundfileService.removeById(id));
    }




    /**
     * 音频流
     * @param id
     * @param res
     * @throws IOException
     */
    @GetMapping("/play/{id}")
    @ApiOperation(value = "播放", notes = "播放")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", required = true)
    })
    public void playSysSoundfile(@PathVariable Integer id , HttpServletResponse res) throws IOException {
        OSSObject isFromOss = sysSoundfileService.getIsFromOss(id);
        res.setContentType("audio/mpeg");

        ServletOutputStream os = res.getOutputStream();

       IOUtils.copy(isFromOss.getObjectContent(),os);
       res.flushBuffer();
    }

}
