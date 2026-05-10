package com.best.practice.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.best.practice.transaction.domain.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {
}
