package cn.siques.mangotask.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangocommon.dto.JsonData;

import cn.siques.mangotask.entity.JobAndTrigger;
import cn.siques.mangotask.entity.JobForm;
import cn.siques.mangotask.service.JobService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * <p>
 * Job Controller
 * </p>
 *
 */
@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping("list")
    public JsonData getJobList(@RequestBody PageRequest pageRequest){
        PageInfo<JobForm> jobList = jobService.getJobList(pageRequest);
        Dict set = Dict.create().set("total", jobList.getTotal()).set("data", jobList.getList());
        return JsonData.buildSuccess(set,HttpStatus.OK.value());
    }


    @GetMapping("test")
    public JsonData test(){
        return JsonData.buildSuccess("成功");
    }

    /**
     * 保存定时任务
     */
    @PostMapping
    public JsonData addJob(@Valid JobForm form) {
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
    @PutMapping(params = "pause")
    public JsonData pauseJob(JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return  JsonData.buildError("参数不能为空", HttpStatus.BAD_REQUEST.value());
        }

        jobService.pauseJob(form);
        return  JsonData.buildSuccess("暂停成功", HttpStatus.OK.value());
    }

    /**
     * 恢复定时任务
     */
    @PutMapping(params = "resume")
    public JsonData resumeJob(JobForm form) throws SchedulerException {
        if (StrUtil.hasBlank(form.getJobGroupName(), form.getJobClassName())) {
            return  JsonData.buildError("参数不能为空", HttpStatus.BAD_REQUEST.value());
        }

        jobService.resumeJob(form);
        return  JsonData.buildSuccess("恢复成功", HttpStatus.OK.value());
    }

    /**
     * 修改定时任务，定时时间
     */
    @PutMapping(params = "cron")
    public JsonData cronJob(@Valid JobForm form) {
        try {
            jobService.cronJob(form);
        } catch (Exception e) {
            return  JsonData.buildError(e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
        return  JsonData.buildSuccess("修改成功", HttpStatus.OK.value());
    }

    @GetMapping
    public JsonData jobList(Integer currentPage, Integer pageSize) {
        if (ObjectUtil.isNull(currentPage)) {
            currentPage = 1;
        }
        if (ObjectUtil.isNull(pageSize)) {
            pageSize = 10;
        }
        PageInfo<JobAndTrigger> all = jobService.list(currentPage, pageSize);
        Dict set = Dict.create().set("total", all.getTotal()).set("data", all.getList());
        return  JsonData.buildSuccess(set,HttpStatus.OK.value());

    }

}
