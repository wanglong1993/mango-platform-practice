package cn.siques.mangotask;

import cn.siques.mangocommon.Page.PageRequest;
import cn.siques.mangotask.entity.JobForm;
import cn.siques.mangotask.service.JobService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SpringBootTest
class MangoTaskApplicationTests {
    @Autowired
    JobService jobService;

    @Test
    void contextLoads() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(1);
        pageRequest.setPageSize(10);
        PageInfo<JobForm> jobList = jobService.getJobList(pageRequest);
        System.out.println(jobList);
    }

}
