package com.best.practice.transaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.best.practice.transaction.domain.entity.OrderItemEntity;
import com.best.practice.transaction.domain.vo.OrderItemVO;

import java.util.List;

public interface OrderItemService extends IService<OrderItemEntity> {
    void saveBatchWithRequestNew(List<OrderItemVO> orderItemVOList);

    void saveBatchWithRequire(List<OrderItemVO> orderItemList);

    void saveBatchWithSupports(List<OrderItemVO> orderItemList);

    void saveBatchWithNotSupported(List<OrderItemVO> orderItemList);

    void saveBatchWithMandatory(List<OrderItemVO> orderItemList);

    void saveBatchWithNever(List<OrderItemVO> orderItemList);

    void saveBatchWithNested(List<OrderItemVO> orderItemList);
}
