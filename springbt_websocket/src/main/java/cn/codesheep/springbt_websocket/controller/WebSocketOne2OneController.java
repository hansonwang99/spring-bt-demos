package cn.codesheep.springbt_websocket.controller;

import cn.codesheep.springbt_websocket.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WebSocketOne2OneController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    // 手动添加信息，然后由服务端推送给用户1
    @GetMapping("/send2User1")
    public String send2User1(@RequestParam String msg) {
        Message message = new Message();
        message.message = msg;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format( new Date() );
        simpMessagingTemplate.convertAndSendToUser( "1", "/user/send", message );
        return "success";
    }

    // 手动添加信息，然后由服务端推送给用户2
    @GetMapping("/send2User2")
    public String send2User2( @RequestParam String msg ) {
        Message message = new Message();
        message.message = msg;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.date = df.format( new Date() );
        simpMessagingTemplate.convertAndSendToUser( "2", "/user/send", message );
        return "success";
    }

}
