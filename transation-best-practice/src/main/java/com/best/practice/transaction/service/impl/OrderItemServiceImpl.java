package com.best.practice.transaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.best.practice.transaction.domain.entity.OrderItemEntity;
import com.best.practice.transaction.domain.vo.OrderItemVO;
import com.best.practice.transaction.mapper.OrderItemMapper;
import com.best.practice.transaction.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItemEntity> implements OrderItemService {

    @Resource
    private ModelMapper modelMapper;


    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void saveBatchWithRequestNew(List<OrderItemVO> orderItemVOList) {
        ArrayList<OrderItemEntity> entities = new ArrayList<>();
        log.info("Child transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("Is actual transaction active: {}", TransactionSynchronizationManager.isActualTransactionActive());
        for(OrderItemVO orderItemVO:orderItemVOList){
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatchWithRequire(List<OrderItemVO> orderItemList) {
        ArrayList<OrderItemEntity> entities = new ArrayList<>();
        log.info("Child transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("Is actual transaction active: {}", TransactionSynchronizationManager.isActualTransactionActive());
        for(OrderItemVO orderItemVO:orderItemList){
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public void saveBatchWithSupports(List<OrderItemVO> orderItemList) {
        ArrayList<OrderItemEntity> entities = new ArrayList<>();
        log.info("Child transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("Is actual transaction active: {}", TransactionSynchronizationManager.isActualTransactionActive());
        int i = 0;
        for(OrderItemVO orderItemVO:orderItemList){
            if (i++>0){
                System.out.println(3/0);
            }
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED)
    public void saveBatchWithNotSupported(List<OrderItemVO> orderItemList) {
        ArrayList<OrderItemEntity> entities = new ArrayList<>();
        log.info("Child transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        log.info("Is actual transaction active: {}", TransactionSynchronizationManager.isActualTransactionActive());
        int i=0;
        for(OrderItemVO orderItemVO:orderItemList){
            if (i++>0){
                System.out.println(3/0);
            }
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.MANDATORY)
    public void saveBatchWithMandatory(List<OrderItemVO> orderItemList) {
        for(OrderItemVO orderItemVO:orderItemList){
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NEVER)
    public void saveBatchWithNever(List<OrderItemVO> orderItemList) {
        for(OrderItemVO orderItemVO:orderItemList){
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NESTED)
    public void saveBatchWithNested(List<OrderItemVO> orderItemList) {
        int i=0;
        for(OrderItemVO orderItemVO:orderItemList){
//            if (i++>0){
//                System.out.println(3/0);
//            }
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }
}
