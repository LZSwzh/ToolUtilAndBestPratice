package com.best.practice.transaction.propagation;

import com.alibaba.fastjson.JSON;
import com.best.practice.transaction.domain.vo.OrderItemVO;
import com.best.practice.transaction.domain.vo.OrderVO;
import com.best.practice.transaction.service.OrderService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test; // 使用JUnit 5的Test注解import javax.annotation.Resource;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RequireNewTest extends PropagationParentTest{

    @Resource
    private OrderService orderService;
    @Test
    public void testChildTransactionWithRequiresNew() {
        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setOrderItemId(null);
        orderItemVO.setOrderId(null);
        orderItemVO.setProductId(1);
        orderItemVO.setSkuId(1);
        orderItemVO.setSkuName("SKU-1");
        orderItemVO.setItemQty(1);
        orderItemVO.setSkuUnitPrice(new BigDecimal("10"));
        orderItemVO.setDiscount(new BigDecimal("0"));
        orderItemVO.setActualPrice(new BigDecimal("10"));
        orderItemVO.setCreatedBy(1);
        orderItemVO.setCreatedDate(LocalDateTime.now());
        orderItemVO.setUpdatedBy(1);
        orderItemVO.setUpdatedDate(LocalDateTime.now());
        orderItemVO.setVersion(1);

        OrderItemVO orderItemVO2 = new OrderItemVO();
        orderItemVO2.setOrderItemId(null);
        orderItemVO2.setOrderId(null);
        orderItemVO2.setProductId(1);
        orderItemVO2.setSkuId(1);
        orderItemVO2.setSkuName("SKU-1-1");
        orderItemVO2.setItemQty(1);
        orderItemVO2.setSkuUnitPrice(new BigDecimal("10"));
        orderItemVO2.setDiscount(new BigDecimal("0"));
        orderItemVO2.setActualPrice(new BigDecimal("10"));
        orderItemVO2.setCreatedBy(1);
        orderItemVO2.setCreatedDate(LocalDateTime.now());
        orderItemVO2.setUpdatedBy(1);
        orderItemVO2.setUpdatedDate(LocalDateTime.now());
        orderItemVO2.setVersion(1);

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(null);
        orderVO.setOrderNum("TEST-PROPAGATION-001");
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

        System.out.printf("REQUIRES_NEW的参数:{%s}", JSON.toJSONString(orderVO));
        orderService.createOrderWithRequireNew(orderVO);
    }
}
