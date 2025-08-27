package com.best.practice.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.best.practice.transaction.domain.entity.OrderEntity;
import com.best.practice.transaction.domain.vo.OrderVO;

public interface OrderService extends IService<OrderEntity> {

    /**
     * 创建订单模式的REQUIRE传播方式
     * @param orderVO
     */
    void createOrderWithRequired(OrderVO orderVO);
    /**
     * 创建订单，内部存在传播方式为 REQUIRED_NEW的函数
     * @param orderVO
     */
    void createOrderWithRequireNew(OrderVO orderVO);

    void createOrderWithSupport(OrderVO orderVO);

    void createOrderWithNotSupported(OrderVO orderVO);

    void createOrderWithMandatory(OrderVO orderVO);

    void createOrderWithNever(OrderVO orderVO);

    void createOrderWithNested(OrderVO orderVO);

    /**
     * 使用TransactionSynchronizationManager手动管理一些行文
     * @param orderVO
     */
    void createOrderWithTransactionSyncManager(OrderVO orderVO);

    void createOrderWithManualTransaction(OrderVO orderVO);
}
