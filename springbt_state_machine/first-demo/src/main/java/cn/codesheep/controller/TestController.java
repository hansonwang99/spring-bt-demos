package cn.codesheep.controller;

import cn.codesheep.constant.Events;
import cn.codesheep.constant.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    StateMachine<States, Events> stateMachine;

    @RequestMapping("/test")
    void doSignals() {
        System.out.println("start before");

        stateMachine.start();

        System.out.println("dosignals");

        stateMachine.sendEvent(Events.EVENT1);
        stateMachine.sendEvent(Events.EVENT2);
    }

}
