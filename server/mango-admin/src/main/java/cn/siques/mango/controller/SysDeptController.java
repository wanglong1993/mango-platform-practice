package cn.siques.mango.controller;

import cn.siques.mango.service.SysDeptService;
import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.dto.JsonData;

import cn.siques.mango.entity.SysDept;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(value="机构管理接口")
@RestController
@RequestMapping("/api/sys/v1/pri/dept")
public class SysDeptController {


    @Autowired
    SysDeptService sysDeptService;

    /**
     * 查询机构树
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dept:view')")
    @GetMapping(value="/findDeptTree")
    public JsonData findNavTree() {
        return JsonData.buildSuccess(sysDeptService.findDeptTree());
    }


    /**
     * 机构分页列表
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dept:view')")
    @PostMapping(value = "/findPage")
    public JsonData findPage(@RequestBody PageRequest pageRequest){
        PageResult result = this.sysDeptService.findPage(pageRequest);

        return JsonData.buildSuccess(result);
    }

    @PreAuthorize("hasAuthority('sys:dept:view')")
    @PostMapping(value = "/findPage/{id}")
    public JsonData findPageById(@PathVariable Long id,@RequestBody PageRequest pageRequest){

        PageResult page = this.sysDeptService.findPageById(pageRequest,id);
        return JsonData.buildSuccess(page);
    }

    @PreAuthorize("hasAuthority('sys:dept:edit')")
    @PostMapping(value = "/save")
    public JsonData save(@RequestBody SysDept sysDept){
        return JsonData.buildSuccess(sysDeptService.save(sysDept));
    }

}
