package cn.siques.mangotask.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import cn.siques.Page.PageRequest;
import cn.siques.dto.JsonData;

import cn.siques.dto.MailDto;
import cn.siques.mangotask.entity.JobAndTrigger;
import cn.siques.mangotask.entity.JobForm;
import cn.siques.mangotask.service.JobService;
import cn.siques.mangotask.service.feign.MailFeignService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * Job Controller
 * </p>
 *
 */
@RestController
@RequestMapping("/api/sys/v1/pri/job")
@Slf4j
public class JobController {
    private final JobService jobService;

    @Resource
    MailFeignService mailFeignService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @GetMapping("test")
    public  JsonData test(){
        MailDto mailDto = new MailDto();
        mailDto.setTo("943452349@qq.com");
        String s = mailFeignService.sendMail(mailDto);
        return JsonData.buildSuccess(s);
    }


    // 查询所有可以执行的任务
    @PreAuthorize("hasAuthority('sys:task:view')")
    @GetMapping("jobList")
    public JsonData jobList(){
        List<JobForm> jobList = jobService.getJobList();
        return JsonData.buildSuccess(jobList,HttpStatus.OK.value());
    }



    /**
     * 生成定时任务
     */
    @PreAuthorize("hasAuthority('sys:task:add')")
    @PostMapping
    public JsonData addJob(@Valid @RequestBody JobForm form) {
        try {
            jobService.addJob(form);
        } catch (Exception e) {
            return  JsonData.buildError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return  JsonData.buildSuccess("操作成功");
    }

    /**
     * 删除定时任务
     */
    @PreAuthorize("hasAuthority('sys:task:edit')")
    @DeleteMapping
    public JsonData deleteJob(JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return  JsonData.buildError("参数不能为空", HttpStatus.BAD_REQUEST.value());
        }

        jobService.deleteJob(form);
        return JsonData.buildSuccess("删除成功");
    }

    /**
     * 暂停定时任务
     */
    @PreAuthorize("hasAuthority('sys:task:edit')")
    @PutMapping(value = "pause")
    public JsonData pauseJob(@RequestBody  JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return  JsonData.buildError("参数不能为空", HttpStatus.BAD_REQUEST.value());
        }

        jobService.pauseJob(form);
        return  JsonData.buildSuccess("暂停成功", HttpStatus.OK.value());
    }

    /**
     * 恢复定时任务
     */
    @PreAuthorize("hasAuthority('sys:task:edit')")
    @PutMapping(value = "resume")
    public JsonData resumeJob(@RequestBody  JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return  JsonData.buildError("参数不能为空", HttpStatus.BAD_REQUEST.value());
        }

        jobService.resumeJob(form);
        return  JsonData.buildSuccess("恢复成功", HttpStatus.OK.value());
    }

    /**
     * 修改定时任务，定时时间
     */
    @PreAuthorize("hasAuthority('sys:task:edit')")
    @PutMapping(value = "cron")
    public JsonData cronJob(@RequestBody @Valid JobForm form) {
        try {
            jobService.cronJob(form);
        } catch (Exception e) {
            return  JsonData.buildError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
        return  JsonData.buildSuccess("修改成功", HttpStatus.OK.value());
    }

    @PreAuthorize("hasAuthority('sys:task:view')")
    @PostMapping("findPage")
    public JsonData jobList(@RequestBody PageRequest pageRequest) {

        PageInfo<JobAndTrigger> all = jobService.list(pageRequest.getPageNum(), pageRequest.getPageSize());
        Dict set = Dict.create().set("totalSize", all.getTotal()).set("content", all.getList());
        return  JsonData.buildSuccess(set,HttpStatus.OK.value());

    }

}
