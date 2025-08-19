package com.best.practice.transaction.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.best.practice.common.exception.MissParamException;
import com.best.practice.transaction.domain.entity.OrderEntity;
import com.best.practice.transaction.domain.vo.OrderItemVO;
import com.best.practice.transaction.domain.vo.OrderVO;
import com.best.practice.transaction.mapper.OrderMapper;
import com.best.practice.transaction.service.OrderItemService;
import com.best.practice.transaction.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService{


    @Resource
    private ModelMapper modelMapper;

    @Resource
    private OrderItemService orderItemService;
    /**
     * 创建订单，内部携带传播方式为REQUIRES_NEW的函数
     * 传播方式：默认的 REQUIRED，存在事务则加入，不存在则新建
     * 上边这个方法出现异常，由于是在一个事务中，因此二者都回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithRequired(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        log.info("Parent transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为REQUIRES_NEW，开启新的事务，二者不干扰
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithRequire(orderItemList);
        //note:模拟异常的出现，观察两个事务注解控制的方法的回滚行为。
        // 第一个内部调用，切面无法管理，因此仍然被这个方法的事务管理
        // 由于第二个service的方法是REQUIRE_NEW,因此尽管出现异常，但是子方法用的是新的事务，和这个无关。
        System.out.println(3/0);
    }

    /**
     * 创建订单，内部携带传播方式为REQUIRES_NEW的函数
     * 传播方式：开启一个新的事务，如果上下文存在事务就挂起
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithRequireNew(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        log.info("Parent transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为REQUIRES_NEW，开启新的事务，二者不干扰
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithRequestNew(orderItemList);
        //note:模拟异常的出现，观察两个事务注解控制的方法的回滚行为。
        // 第一个内部调用，切面无法管理，因此仍然被这个方法的事务管理
        // 由于第二个service的方法是REQUIRE_NEW,因此尽管出现异常，但是子方法用的是新的事务，和这个无关。
        System.out.println(3/0);
    }



    /**
     * REQUIRES_NEW：名义上这个是创建新的事务，但是这个方法被内部调用，注解不会生效。
     * 实际上仍然走的是createOrder的事务中
     * @param orderVO
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void insertOrderVO(OrderVO orderVO) {
        OrderEntity orderEntity = modelMapper.map(orderVO, OrderEntity.class);
        baseMapper.insert(orderEntity);
        orderVO.setOrderId(orderEntity.getOrderId());
        log.info("创建订单成功：{}", orderEntity);
    }


    /**
     * 创建订单，内部携带传播方式为SUPPORTS的函数
     * 传播方式：存在事务则加入，不存在则以非事务方式执行
     */
    @Override
//    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithSupport(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        log.info("Parent transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为REQUIRES_NEW，开启新的事务，二者不干扰
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithSupports(orderItemList);
        //note:模拟异常的出现，观察两个事务注解控制的方法的回滚行为。
        // 第一个内部调用，切面无法管理，因此仍然被这个方法的事务管理
        // 由于第二个service的方法是REQUIRE_NEW,因此尽管出现异常，但是子方法用的是新的事务，和这个无关。
    }

    /**
     * 创建订单，内部携带传播方式为SUPPORTS的函数
     * 传播方式:不支持事务，如果存在事务会挂起当前事务
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithNotSupported(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        log.info("Parent transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为NOT_SUPPORTED,挂起当前事务并以非事务方式执行,也就是子事务的异常不会回滚子事务，但会回滚父事务
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithNotSupported(orderItemList);
    }

    /**
     * 当前上下文存在事务则加入,不存在则报错
     * 由于父方法不存在事务，因此成功插入，而子方法直接报错了
     * @param orderVO
     */
    @Override
    public void createOrderWithMandatory(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        log.info("Parent transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为NOT_SUPPORTED,挂起当前事务并以非事务方式执行,也就是子事务的异常不会回滚子事务，但会回滚父事务
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithMandatory(orderItemList);
    }


    /**
     * 不支持事务，当前上下文存在事务则则报错
     * 子方法直接报错，但是父方法也被事务管控，由于没有手动try-catch因此父子都回滚
     *
     * @param orderVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithNever(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        log.info("Parent transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为NOT_SUPPORTED,挂起当前事务并以非事务方式执行,也就是子事务的异常不会回滚子事务，但会回滚父事务
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithNever(orderItemList);
    }

    /**
     * 如果上下存在事务，则开启一个嵌套驶入，父方法异常，二者都会滚，子方法异常只回滚子方法
     * @param orderVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithNested(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        log.info("Parent transaction started: {}", TransactionSynchronizationManager.getCurrentTransactionName());
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为REQUIRES_NEW，开启新的事务，二者不干扰
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithNested(orderItemList);
        //note:模拟异常的出现，观察两个事务注解控制的方法的回滚行为。
        System.out.println(3/0);
    }
}
