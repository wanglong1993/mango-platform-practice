package cn.siques.mango.controller;



import cn.siques.mango.controller.dto.DbConfig;
import cn.siques.mango.service.SysCodeGenService;

import cn.siques.mango.utils.DbUtil;
import cn.siques.Page.PageRequest;
import cn.siques.Page.PageResult;
import cn.siques.dto.JsonData;
import cn.siques.mango.entity.gen.GenConfig;
import io.swagger.annotations.Api;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(value="代码生成器接口")
@RestController
@RequestMapping("/api/sys/v1/pri/codeGen")
public class SysCodeGenController {
    private   Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SysCodeGenService sysCodeGenService;

    @PostMapping("findPage")
    public JsonData findPage(@RequestBody PageRequest pageRequest){
        logger.info(sysCodeGenService.findPage(pageRequest).getContent().toString());
        PageResult page = sysCodeGenService.findPage(pageRequest);
        return  JsonData.buildSuccess(page);
    }

    @Autowired
    DbUtil dbUtil;


    @PostMapping("generate")
    public void generate(@RequestBody GenConfig genConfig, HttpServletResponse response){
        byte[] data = sysCodeGenService.generatorCode(genConfig);

        response.reset();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.zip", genConfig.getTableName()));
        response.addHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(data.length));
        response.setContentType("application/octet-stream; charset=UTF-8");

        IoUtil.write(response.getOutputStream(), Boolean.TRUE, data);
    }

    @GetMapping("dblist")
    public JsonData queryDbList(){
        logger.info(sysCodeGenService.queryDbList().toString());
      return JsonData.buildSuccess(sysCodeGenService.queryDbList());
    }

    @PostMapping("changeSource")
    public JsonData changeSource(@RequestBody DbConfig dbConfig){

        dbUtil.setDbSource(dbConfig);
        PageRequest pageRequest = new PageRequest();
        PageResult page = sysCodeGenService.findPage(pageRequest);
        return JsonData.buildSuccess(page);
    }


}
