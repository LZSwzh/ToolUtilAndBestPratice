package com.best.pratice.design.basicImpl.action.strategyDesign;

import java.math.BigDecimal;

/**
 * 定义一个上下文类，持有一个策略接口
 */
public class PayContext {
    PayStrategy payStrategy;

    public PayContext(PayStrategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    /**
     * 执行支付这个动作
     */
    public void execute(BigDecimal money){
        payStrategy.payMoney(money);
    }
}
