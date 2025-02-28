package fsm.byspring.linstener;

import fsm.byspring.pojo.Order;
import fsm.byspring.pojo.enums.OrderEvent;
import fsm.byspring.pojo.enums.OrderState;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * 订单状态机监听器
 */
@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
public class OrderStateListenerImpl {
    /** 监听状态从待支付到待送货的事件  */
    @OnTransition(source = "WAIT_PAY", target = "WAIT_SHIP")
    public boolean payTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setState(OrderState.WAIT_SHIP);
        System.out.println("支付，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }

    /** 监听状态从待送货到待收货的事件  */
    @OnTransition(source = "WAIT_SHIP", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setState(OrderState.WAIT_RECEIVE);
        System.out.println("发货，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }

    /** 监听状态从待收获到完成的事件  */
    @OnTransition(source = "WAIT_RECEIVE", target = "FINISHED")
    public boolean receiveTransition(Message<OrderEvent> message){
        Order order = (Order) message.getHeaders().get("order");
        order.setState(OrderState.FINISHED);
        System.out.println("收货，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }
}
