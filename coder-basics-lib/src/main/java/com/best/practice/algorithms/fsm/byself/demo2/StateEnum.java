package com.best.practice.algorithms.fsm.byself.demo2;


public enum StateEnum {
    // 初始化
    INIT,

    // 待支付
    WAIT_PAY,

    // 待接单
    WAIT_ACCEPT,

    // 待发货
    WAIT_SHIP,

    // 待收货
    WAIT_RECEIVE,

    // 退货中
    RETURNING,

    // 交易完成
    COMPLETED,

    // 取消订单
    CANCELLED;

}
