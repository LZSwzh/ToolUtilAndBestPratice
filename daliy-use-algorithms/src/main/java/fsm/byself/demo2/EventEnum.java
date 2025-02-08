package fsm.byself.demo2;


public enum EventEnum {
    // 创建订单
    CREATE_ORDER,

    // 支付
    PAY_SUCCESS,

    // 接单
    ACCEPT_ORDER,

    // 发货
    SHIP_ORDER,

    // 确认收货
    CONFIRM_RECEIPT,


    // 用户退货
    RETURN,

    //  商家确认退货
    ACCEPT_RETURN,


    // 取消订单
    CANCEL_ORDER;
}
