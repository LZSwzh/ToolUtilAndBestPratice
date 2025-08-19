package com.best.practice.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.best.practice.transaction.domain.entity.OrderPaymentFlowEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderPaymentFlowMapper extends BaseMapper<OrderPaymentFlowEntity> {
}
