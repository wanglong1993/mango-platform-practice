package cn.siques.mango.controller;

import cn.siques.mango.entity.JsonData;
import cn.siques.mango.entity.SysUser;
import cn.siques.mango.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

@RestController
@RequestMapping("/api/sys/v1/pub/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("list")
    public JsonData findAll(){
        List<SysUser> all = sysUserService.findAll();

        return JsonData.buildSuccess(all);
    }

}
