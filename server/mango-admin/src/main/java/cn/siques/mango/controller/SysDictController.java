package cn.siques.mango.controller;

import cn.siques.mango.annotation.SaveDate;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.dto.JsonData;
import cn.siques.mangocore.entity.SysDict;
import cn.siques.mango.service.SysDictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sys/v1/pri/dict")
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    @PreAuthorize("hasAuthority('sys:dict:add') AND hasAnyAuthority('sys:dict:edit')")
    @PostMapping("save")
    public JsonData save(@RequestBody SysDict sysDict){
        return JsonData.buildSuccess(sysDictService.save(sysDict));
    }

    /**
     * 列表删除
     * @param records
     * @return
     */
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    @PostMapping("delete")
    public JsonData delete(@RequestBody List<SysDict> records){
        return JsonData.buildSuccess(sysDictService.delete(records));
    }

    @PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping("findPage")
    public JsonData findPage(@RequestBody PageRequest pageRequest){
        return JsonData.buildSuccess(sysDictService.findPage(pageRequest));

    }

    @PreAuthorize("hasAuthority('sys:dict:view')")
    @GetMapping("findByLabel")
    public JsonData findByLabel(@RequestParam String label){
        return JsonData.buildSuccess(sysDictService.findByLabel(label));
    }
}
