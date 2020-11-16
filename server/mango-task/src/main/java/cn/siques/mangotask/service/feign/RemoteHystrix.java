package cn.siques.mangotask.service.feign;

import cn.siques.mangocommon.dto.MailDto;
import org.springframework.stereotype.Component;

@Component
public class RemoteHystrix implements MailFeignService {

    @Override
    public String sendMail(MailDto mailDto) {
        return "服务暂不可用";
    }
}
