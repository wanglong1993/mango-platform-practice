package cn.siques.mango.controller;

import cn.siques.mango.entity.JsonData;
import cn.siques.mango.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import cn.siques.mangocore.Page.PageRequest;
import cn.siques.mangocore.Page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@RestController
@RequestMapping("/api/sys/v1/pri/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("list")
    public JsonData findAll(){
        List<SysUser> all = sysUserService.findAll();

        return JsonData.buildSuccess(all);
    }

    @PostMapping("/findPage")
    public JsonData findPage(@RequestBody PageRequest pageRequest){
        PageResult page = sysUserService.findPage(pageRequest);
        System.out.println(page);
        return JsonData.buildSuccess(page);
    }



}
