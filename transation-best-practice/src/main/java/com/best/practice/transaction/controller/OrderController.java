package com.best.practice.transaction.controller;

import com.best.practice.transaction.domain.vo.OrderVO;
import com.best.practice.transaction.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping(value = "restful/order/createWithRequestNew")
    public void createOrderWithRequestNew(@RequestBody OrderVO orderVO) {
        orderService.createOrderWithRequireNew(orderVO);
    }
}
