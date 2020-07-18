package cn.siques.mango.controller;

import cn.siques.mango.service.SysDeptService;
import cn.siques.mangocore.http.JsonData;
import cn.siques.mangocore.utils.SecurityUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
