package cn.siques.mangotask.job;


import cn.hutool.core.date.DateUtil;
import cn.siques.mangotask.config.Desc;
import cn.siques.mangotask.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Hello Job
 * </p>
 */
@Slf4j
@Component
//@Desc("Hello Job任务")
public class HelloJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) {
        log.error("Hello Job 执行时间: {}", DateUtil.now());
    }
}