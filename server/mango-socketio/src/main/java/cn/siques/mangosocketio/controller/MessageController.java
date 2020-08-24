package cn.siques.mangosocketio.controller;



import cn.siques.mangocommon.dto.JsonData;
import cn.siques.mangosocketio.handler.MessageEventHandler;
import cn.siques.mangosocketio.payload.BroadcastMessageRequest;
import com.corundumstudio.socketio.BroadcastOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sys/v1/pri/send")
public class MessageController {

    @Autowired
    private MessageEventHandler eventHandler;

    @PostMapping("/broadcast")
    public JsonData broadcast(@RequestBody BroadcastMessageRequest message){
        if(message.getMessage()==null){
            return JsonData.buildError("参数为空");
        }
        eventHandler.sendToBroadcast(message);
      return JsonData.buildSuccess("发送成功");
    }
}
