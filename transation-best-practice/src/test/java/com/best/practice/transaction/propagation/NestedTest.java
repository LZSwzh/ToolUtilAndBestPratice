package com.best.practice.transaction.propagation;

import com.best.practice.transaction.domain.vo.OrderItemVO;
import com.best.practice.transaction.domain.vo.OrderVO;
import com.best.practice.transaction.service.OrderService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NestedTest extends PropagationParentTest{
    @Resource
    private OrderService orderService;
    @Test
    public void testChildTransactionWithNested(){
        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setOrderItemId(null);
        orderItemVO.setOrderId(null);
        orderItemVO.setProductId(2);
        orderItemVO.setSkuId(1);
        orderItemVO.setSkuName("SKU-7");
        orderItemVO.setItemQty(2);
        orderItemVO.setSkuUnitPrice(new BigDecimal("10"));
        orderItemVO.setDiscount(new BigDecimal("0"));
        orderItemVO.setActualPrice(new BigDecimal("10"));
        orderItemVO.setCreatedBy(1);
        orderItemVO.setCreatedDate(LocalDateTime.now());
        orderItemVO.setUpdatedBy(1);
        orderItemVO.setUpdatedDate(LocalDateTime.now());
        orderItemVO.setVersion(1);

        OrderItemVO orderItemVO2 = new OrderItemVO();
        orderItemVO.setOrderItemId(null);
        orderItemVO.setOrderId(null);
        orderItemVO.setProductId(2);
        orderItemVO.setSkuId(1);
        orderItemVO.setSkuName("SKU-7-1");
        orderItemVO.setItemQty(2);
        orderItemVO.setSkuUnitPrice(new BigDecimal("10"));
        orderItemVO.setDiscount(new BigDecimal("0"));
        orderItemVO.setActualPrice(new BigDecimal("10"));
        orderItemVO.setCreatedBy(1);
        orderItemVO.setCreatedDate(LocalDateTime.now());
        orderItemVO.setUpdatedBy(1);
        orderItemVO.setUpdatedDate(LocalDateTime.now());
        orderItemVO.setVersion(1);

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(null);
        orderVO.setOrderNum("TEST-PROPAGATION-007");
        orderVO.setOrderQty(2);
        orderVO.setTotalAmount(new BigDecimal("10"));
        orderVO.setPayAmount(new BigDecimal("10"));
        orderVO.setStatus("DRAFT");
        orderVO.setCreatedBy(1);
        orderVO.setCreatedDate(LocalDateTime.now());
        orderVO.setUpdatedBy(1);
        orderVO.setUpdatedDate(LocalDateTime.now());
        orderVO.setVersion(1);
        orderVO.setOrderItemList(Lists.newArrayList(orderItemVO,orderItemVO2));
        orderService.createOrderWithNested(orderVO);
    }
}
