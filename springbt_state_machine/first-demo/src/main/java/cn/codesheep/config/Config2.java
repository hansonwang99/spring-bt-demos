package cn.codesheep.config;

import cn.codesheep.bean.Recruit;
import cn.codesheep.constant.Events;
import cn.codesheep.constant.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

@Configuration
@Scope("prototype")
@EnableStateMachineFactory(name = "recruitStateMachineFactory")
public class Config2 extends EnumStateMachineConfigurerAdapter<States, Events> {

    /**订单状态机ID*/
    public static final String orderStateMachineId = "recruitStateMachineId";

    @Override
    public void configure( StateMachineStateConfigurer<States, Events> states )
            throws Exception {
        states
                .withStates()
                .initial(States.STATE1)
                .states( EnumSet.allOf(States.class) );
    }

    @Override
    public void configure( StateMachineTransitionConfigurer<States, Events> transitions )
            throws Exception {
        transitions
                .withExternal()
                .source(States.STATE1).target(States.STATE2)
                .event(Events.EVENT1)
                .and()
                .withExternal()
                .source(States.STATE2).target(States.STATE1)
                .event(Events.EVENT2);
    }

    @Bean
    public StateMachinePersister<States, Events, Recruit> persister() {
        return new DefaultStateMachinePersister<States, Events, Recruit>( new StateMachinePersist<States, Events, Recruit>() {

            @Override
            public void write( StateMachineContext<States, Events> context, Recruit order ) throws Exception {
                //此处并没有进行持久化操作
                //order.setStatus(context.getState());
            }
            @Override
            public StateMachineContext<States, Events> read(Recruit order) throws Exception {
                //此处直接获取order中的状态，其实并没有进行持久化读取操作
                StateMachineContext<States, Events> result = new DefaultStateMachineContext<States, Events>(order.getStates(), null, null, null, null, orderStateMachineId);
                return result;
            }

        });
    }
}