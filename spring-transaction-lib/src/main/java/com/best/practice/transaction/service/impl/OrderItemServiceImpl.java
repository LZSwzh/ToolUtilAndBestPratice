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
        //note:模拟子方法异常,如果子方法异常,父子方法都回滚了
        int idx = 0;
        for(OrderItemVO orderItemVO:orderItemVOList){
            if (idx++>0){
                System.out.println(3/0);
            }
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatchWithRequire(List<OrderItemVO> orderItemList) {
        ArrayList<OrderItemEntity> entities = new ArrayList<>();
        for(OrderItemVO orderItemVO:orderItemList){
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            //note:Required传播方式下，子方法出现异常，父子都回滚
            System.out.println(3/0);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.SUPPORTS)
    public void saveBatchWithSupports(List<OrderItemVO> orderItemList) {
        ArrayList<OrderItemEntity> entities = new ArrayList<>();
        int i = 0;
        for(OrderItemVO orderItemVO:orderItemList){
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            //note:如果上下文存在事务,supports以事务方法执行，如果内部异常会回滚
            if (i++>0){
//                System.out.println(3/0);
            }
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NOT_SUPPORTED)
    public void saveBatchWithNotSupported(List<OrderItemVO> orderItemList) {
        ArrayList<OrderItemEntity> entities = new ArrayList<>();
        int i=0;
        //note:NOT_SUPPORTED自身以非事务执行，如果上下文存在事务会挂起，此期间子方法失败会回滚父方法
        for(OrderItemVO orderItemVO:orderItemList){
            if (i++>0){
//                System.out.println(3/0);
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
//            System.out.println(3/0);
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
            //note:模拟异常，内部方法异常，如果父不try-catch,父子都回滚；如果catch仅回滚子
            if (i++>0){
                System.out.println(3/0);
            }
            OrderItemEntity entity = modelMapper.map(orderItemVO, OrderItemEntity.class);
            baseMapper.insert(entity);
            orderItemVO.setOrderItemId(entity.getOrderItemId());
        }
    }
}
