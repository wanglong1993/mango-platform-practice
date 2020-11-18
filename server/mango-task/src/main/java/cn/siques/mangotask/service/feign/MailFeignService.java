package cn.siques.mangotask.service.feign;
import cn.siques.dto.MailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "mango-admin", fallback=RemoteHystrix.class)
public interface MailFeignService {

    @PostMapping("/api/sys/v1/pub/mail/send")
    String sendMail(MailDto mailDto);
}
