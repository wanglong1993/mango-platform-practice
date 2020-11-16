package cn.siques.mango.controller;

import cn.siques.mango.service.SysLogService;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.dto.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("/api/sys/v1/pri/log")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

        @PreAuthorize("hasAuthority('sys:log:view')")
        @PostMapping("/findPage")
        public JsonData findPage(PageRequest pageRequest){
            PageResult page = sysLogService.findPage(pageRequest);
            return JsonData.buildSuccess(page);
        }
}
