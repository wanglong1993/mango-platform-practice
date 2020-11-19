package cn.siques.mangotask;

import cn.siques.Page.PageRequest;
import cn.siques.mangotask.service.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MangoTaskApplicationTests {
    @Autowired
    JobService jobService;

    @Test
    void contextLoads() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(1);
        pageRequest.setPageSize(10);

    }

}
