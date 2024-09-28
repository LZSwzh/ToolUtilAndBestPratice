package com.best.pratice.design.basicImpl.action.strategyDesign;

import java.math.BigDecimal;

/**
 * 定义一个支付策略接口
 */
public interface PayStrategy {
    void payMoney(BigDecimal money);
}
