package com.best.pratice.design.basicImpl.action.strategyDesign.strategyImpl;

import com.best.pratice.design.basicImpl.action.strategyDesign.PayStrategy;

import java.math.BigDecimal;

public class WechatPayStrategy implements PayStrategy {
    public void payMoney(BigDecimal money) {
        System.out.println("微信支付步骤1——调用微信支付相关的API，显示前端界面");
        System.out.println("微信支付步骤2——根据微信计算比例计算需要扣减的费用");
        System.out.println("发起远程调用3——调用微信API扣减银行卡/余额");
    }
}
