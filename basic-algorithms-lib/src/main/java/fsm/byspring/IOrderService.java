package fsm.byspring;

import fsm.byspring.pojo.Order;

import java.util.Map;

public interface IOrderService {
    /** 创建订单*/
    Order createOrder();
    /** 支付*/
    Order pay(Long id);
    /** 送货*/
    Order ship(Long id);
    /** 收获*/
    Order receive(Long id);
    /** 获取所有订单信息*/
    Map<Long, Order> getOrderMap();
}
