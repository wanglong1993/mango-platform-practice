package cn.siques.mangomail.handler;



import cn.siques.dto.MailDto;

import cn.siques.mangomail.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Component
@RabbitListener(queues = "mail.queue")
public class MailHandler {
    @Autowired
    MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;


    @RabbitHandler
    public void directHandlerAutoAck(MailDto mailDto) throws MessagingException {

        Context context = new Context();
        context.setVariable("project", "Spring Boot Demo");
        context.setVariable("author", "Shenghao He");
        context.setVariable("url", "https://github.com/ericheshenghao/mango-platform-practice");

        String emailTemplate = templateEngine.process("welcome", context);
        mailService.sendHtmlMail(mailDto.getTo(), "这是一封模板HTML邮件", emailTemplate);

    }

}
