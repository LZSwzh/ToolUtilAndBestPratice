package com.best.practice.algorithms.fsm.byspring;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.best.practice.algorithms.fsm.byspring.pojo.Order;
import com.best.practice.algorithms.fsm.byspring.pojo.enums.OrderEvent;
import com.best.practice.algorithms.fsm.byspring.pojo.enums.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private StateMachine<OrderState, OrderEvent> orderStateMachine;

    @Autowired
    private StateMachinePersister<OrderState, OrderEvent, Order> persister;

    private Map<Long, Order> orders = new HashMap<>();

    /** 创建订单  */
    @Override
    public Order createOrder() {
        Order order = new Order();
        order.setState(OrderState.WAIT_PAY);
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        order.setId(snowflake.nextId());
        orders.put(order.getId(), order);
        return order;
    }

    /** 支付  */
    @Override
    public Order pay(Long id) {
        Order order = orders.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试支付，订单号：" + id);
        Message message = MessageBuilder.withPayload(OrderEvent.PAY).
                setHeader("order", order).build();
        if (!sendEvent(message, order)) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 支付失败, 状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order ship(Long id) {
        Order order = orders.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试发货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderEvent.SHIP)
                .setHeader("order", order).build(), orders.get(id))) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 发货失败，状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Order receive(Long id) {
        Order order = orders.get(id);
        System.out.println("线程名称：" + Thread.currentThread().getName() + " 尝试收货，订单号：" + id);
        if (!sendEvent(MessageBuilder.withPayload(OrderEvent.RECEVIED)
                .setHeader("order", order).build(), orders.get(id))) {
            System.out.println("线程名称：" + Thread.currentThread().getName() + " 收货失败，状态异常，订单号：" + id);
        }
        return orders.get(id);
    }

    @Override
    public Map<Long, Order> getOrderMap() {
        return orders;
    }

    /**
     * 发送订单状态转换事件
     *
     * @param message
     * @param order
     * @return
     */
    private synchronized boolean sendEvent(Message<OrderEvent> message, Order order) {
        boolean result = false;
        try {
            orderStateMachine.start();
            //尝试恢复状态机状态
            persister.restore(orderStateMachine, order);
            //添加延迟用于线程安全测试
            Thread.sleep(1000);
            result = orderStateMachine.sendEvent(message);
            //持久化状态机状态
            persister.persist(orderStateMachine, order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }
}
