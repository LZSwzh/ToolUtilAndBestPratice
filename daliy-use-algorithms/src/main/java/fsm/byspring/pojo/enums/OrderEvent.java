package fsm.byspring.pojo.enums;

/**
 * 事件，能够触发状态转移的事件
 */
public enum OrderEvent {
    /** 支付 */
    PAY,
    /** 发货 */
    SHIP,
    /** 收货 */
    RECEVIED;
}
