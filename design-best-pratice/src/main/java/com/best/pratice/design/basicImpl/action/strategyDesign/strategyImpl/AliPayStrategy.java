package com.best.pratice.design.basicImpl.action.strategyDesign.strategyImpl;

import com.best.pratice.design.basicImpl.action.strategyDesign.PayStrategy;

import java.math.BigDecimal;

public class AliPayStrategy implements PayStrategy {
    private static BigDecimal bd = new BigDecimal(0.03);
    public void payMoney(BigDecimal money) {
        System.out.println("微信支付步骤1——调用支付宝相关的API，显示前端界面");
        System.out.println("微信支付步骤2——根据支付宝的计算比例计算需要扣减的费用");
        System.out.println("发起远程调用3——调用支付宝API扣减银行卡/余额");
        System.out.println("接受异步回调————支付成功消息");
    }
}
