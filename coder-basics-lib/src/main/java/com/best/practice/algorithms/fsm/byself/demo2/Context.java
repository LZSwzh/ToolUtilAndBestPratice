package com.best.practice.algorithms.fsm.byself.demo2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * 动作需要使用的上下文,下单所需要的上下文可能有：商品类型、购买数量、商品名称
 */
@Getter
@Setter
@AllArgsConstructor
public class Context {


    /** 商品名称 */
    private String name;

    /** 商品类型 */
    private String orderType;

    /** 购买数量 */
    private Integer purCount;


    @Override
    public String toString() {
        return "[商品名称:"+name+",类型:"+orderType+",数量:"+purCount+"]";
    }
}
