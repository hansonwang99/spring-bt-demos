package cn.codesheep.controller;

import cn.codesheep.bean.Recruit;
import cn.codesheep.constant.Events;
import cn.codesheep.constant.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController2 {

    ThreadLocal<StateMachine> stateMachineThreadLocal  = new ThreadLocal<StateMachine>();

    @Autowired
    @Qualifier("recruitStateMachineFactory" )
    StateMachineFactory<States, Events> stateMachineFactory;

    @Autowired
    private StateMachinePersister<States, Events,Recruit> persister;

    @RequestMapping("/hello2")
    void doSignals() {
        System.out.println("start before");
        Recruit recruit = new Recruit();
        StateMachine stateMachinet = getStateMachine(recruit);
        System.out.println("dosignals stateMachinet hashcode "+stateMachinet.hashCode()+"  recruit hashcode is "+recruit.hashCode());
        stateMachinet.sendEvent(Events.EVENT1);
        stateMachinet.sendEvent(Events.EVENT2);
    }

    private StateMachine getStateMachine(Recruit recruit){
        StateMachine machine = stateMachineThreadLocal.get();
        if (null == machine){
            machine = stateMachineFactory.getStateMachine("recruitStateMachineId");
        }
        try {
            machine.start();
            persister.restore(machine,recruit);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return machine;
    }

    void sendEvent(Events events, Recruit recruit){
        Message message = MessageBuilder.withPayload(events).setHeader("recruit", recruit).build();
        StateMachine stateMachine = getStateMachine(recruit);
        stateMachine.sendEvent(message);
    }

    @RequestMapping("/sendEvent1")
    void sendEvent(){
        Recruit recruit = new Recruit();
        recruit.setStates(States.STATE1);
        StateMachine stateMachine = getStateMachine(recruit);
        stateMachine.sendEvent(Events.EVENT1);

    }
    @RequestMapping("/sendEvent2")
    void sendEvent2(){
        Recruit recruit = new Recruit();
        recruit.setStates(States.STATE2);
        StateMachine stateMachine = getStateMachine(recruit);
        stateMachine.sendEvent(Events.EVENT2);

    }

    @RequestMapping("/sendEvent3")
    void sendEvent3(){
        Recruit recruit = new Recruit();
        recruit.setContext("hello world");
        recruit.setStates(States.STATE2);

        sendEvent( Events.EVENT2, recruit );

    }

}
