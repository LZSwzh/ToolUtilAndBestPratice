package fsm.byspring.config;

import fsm.byspring.pojo.Order;
import fsm.byspring.pojo.enums.OrderEvent;
import fsm.byspring.pojo.enums.OrderState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * 订单状态机配置
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {
    /**
     * 配置状态
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        //配置初始状态为WAIT_PAY,所有的状态有OrderState
        states.withStates()
              .initial(OrderState.WAIT_PAY)
              .states(EnumSet.allOf(OrderState.class));
    }
    /**
     * 配置状态流转关系
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderState.WAIT_PAY).target(OrderState.WAIT_SHIP).event(OrderEvent.PAY)
                .and().withExternal()
                .source(OrderState.WAIT_SHIP).target(OrderState.WAIT_RECEIVE).event(OrderEvent.SHIP)
                .and().withExternal()
                .source(OrderState.WAIT_RECEIVE).target(OrderState.FINISHED).event(OrderEvent.RECEVIED);
    }
    /**
     * 状态持久化配置
     */
    @Bean
    public DefaultStateMachinePersister persister(){
        return new DefaultStateMachinePersister<>(new StateMachinePersist<Object, Object, Order>() {
            @Override
            public void write(StateMachineContext<Object, Object> context, Order order) throws Exception {
                //此处并没有进行持久化操作
            }

            @Override
            public StateMachineContext<Object, Object> read(Order order) throws Exception {
                //此处直接获取Order中的状态，其实并没有进行持久化读取操作
                return new DefaultStateMachineContext(order.getState(), null, null, null);
            }
        });
    }
}
