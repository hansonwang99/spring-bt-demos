package cn.codesheep.bean;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.messaging.Message;

@WithStateMachine(name = "recruitStateMachineId")
public class MyBean2 {

    @OnTransition(source="STATE2" ,target = "STATE1")
    public void toState1(Message message) {
        Recruit recruit = (Recruit) message.getHeaders().get("recruit");
        System.out.println("toState1 " +Thread.currentThread().toString() + recruit.getContext() );
    }

    @OnTransition(source="" ,target = "STATE1")
    public void initState() {

        System.out.println("toState1 "+Thread.currentThread().toString());
    }

    @OnTransition(source="STATE1",target = "STATE2")
    public  void toState2() throws Exception {
        System.out.println("toState2 "+Thread.currentThread().toString());
    }

}