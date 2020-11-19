package cn.siques.mangotask.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author : heshenghao
 * @date : 15:58 2020/10/26
 */
@Component
@FeignClient(value = "mango-admin", fallback=RemoteHystrix.class)
public interface CrawlerFeignService {

    @PostMapping("/api/sys/v1/pub/")
    int crawlerStart();
}
