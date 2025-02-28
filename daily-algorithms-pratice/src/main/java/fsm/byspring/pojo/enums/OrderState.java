package fsm.byspring.pojo.enums;

/**
 * 订单状态枚举
 */
public enum OrderState {
    /** 待支付 */
    WAIT_PAY,
    /** 待发货  */
    WAIT_SHIP,
    /** 待收货 */
    WAIT_RECEIVE,

    /** 结束 */
    FINISHED;
}
