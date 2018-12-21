package cn.codesheep.springbt_websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user1")
    public String index1() {
        return "index1";
    }

    @GetMapping("/user2")
    public String index3() {
        return "index2";
    }
}
