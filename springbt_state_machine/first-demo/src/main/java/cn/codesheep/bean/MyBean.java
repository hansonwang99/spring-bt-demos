package cn.codesheep.bean;

import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
public class MyBean {

    @OnTransition(source="STATE2" ,target = "STATE1")
    public void toState1() {
        System.out.println("toState1");
    }

    @OnTransition(source="" ,target = "STATE1")
    public void initState() {
        System.out.println("toState1");
    }

    @OnTransition(source="STATE1",target = "STATE2")
    public  void toState2() throws Exception {
        System.out.println("toState2");
    }
}