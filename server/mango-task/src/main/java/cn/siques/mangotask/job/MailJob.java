package cn.siques.mangotask.job;


import cn.hutool.core.date.DateUtil;
import cn.siques.mangocommon.dto.MailDto;
import cn.siques.mangotask.config.Desc;
import cn.siques.mangotask.entity.JobForm;
import cn.siques.mangotask.job.base.BaseJob;
import cn.siques.mangotask.service.JobService;
import cn.siques.mangotask.service.feign.MailFeignService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
@Desc("邮件发送任务")
public class MailJob implements BaseJob {
    @Resource
    MailFeignService mailFeignService;

    @Autowired
    JobService jobService;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        log.info("Mail Job 执行时间: {}", DateUtil.now());
        MailDto mailDto = new MailDto();
        mailDto.setTo("943452349@qq.com");

        try {
            mailFeignService.sendMail(mailDto);
        }catch (Exception e){
            JobDetail jobDetail = context.getJobDetail();

            JobKey key = jobDetail.getKey();

            JobForm jobForm = new JobForm(key.getName(), key.getGroup());
            jobService.pauseJob(jobForm);
        }


    }
}
