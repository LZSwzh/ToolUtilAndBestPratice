package com.best.practice.design.behavioral.strategy.basic;

import java.math.BigDecimal;

/**
 * 定义一个支付策略接口
 */
public interface PayStrategy {
    void payMoney(BigDecimal money);
}
