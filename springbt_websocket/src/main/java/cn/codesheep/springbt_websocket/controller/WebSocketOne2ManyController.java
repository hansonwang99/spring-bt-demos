package cn.codesheep.springbt_websocket.controller;


import cn.codesheep.springbt_websocket.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@EnableScheduling
public class WebSocketOne2ManyController {  // 一对多 websocket通信，即服务端群发信息给前端

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 手动添加信息，然后由服务端推送给所有客戶端
    @GetMapping("/send2All")
    public String send2All( @RequestParam String msg ) {
        Message message = new Message();
        message.message = msg;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format( new Date() );
        messagingTemplate.convertAndSend( "/topic/send", message );
        return "success";
    }

    // 前端发送信息到后端，触发后端再把信息吐回给前端
    @MessageMapping("/send") // 浏览器向服务器发送请求时，通过 MessageMapping映射 /socket地址
    @SendTo("/topic/send")   // 当服务器有消息时，会对订阅了 @SendTo中路径的浏览器发送消息
    public Message send(Message message) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format(new Date());
        return message;
    }

    // 服务端定时发送信息给前端，一对多，每隔1s
    @Scheduled(fixedRate = 1000)
    @SendTo("/topic/timer")
    public Object timer() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        messagingTemplate.convertAndSend("/topic/timer", df.format(new Date()));  // 一对多发送，一对一发送则用convertAndSendToUser()
        return "timer";
    }

}
