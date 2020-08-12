package cn.siques.mango.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.db.Entity;
import cn.siques.mango.service.SysCodeGenService;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.Page.PageResult;
import cn.siques.mangocommon.dto.JsonData;
import cn.siques.mangocore.entity.gen.GenConfig;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value="代码生成器接口")
@RestController
@RequestMapping("/api/sys/v1/pri/codeGen")
public class SysCodeGenController {
    @Autowired
    SysCodeGenService sysCodeGenService;

    @PostMapping("findPage")
    public JsonData findPage(@RequestBody PageRequest pageRequest){
        PageResult page = sysCodeGenService.findPage(pageRequest);
        return  JsonData.buildSuccess(page);
    }

    @SneakyThrows
    @PostMapping("generate")
    public void generate(@RequestBody GenConfig genConfig, HttpServletResponse response){
        byte[] data = sysCodeGenService.generatorCode(genConfig);

        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);

    }

}
