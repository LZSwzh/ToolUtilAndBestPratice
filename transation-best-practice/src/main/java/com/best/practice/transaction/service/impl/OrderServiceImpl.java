package com.best.practice.transaction.service.impl;

import cn.hutool.core.collection.CollUtil;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.*;

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
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        //note:如果子方法可能异常,手动try-catch测试和NESTED的区别,即使手动try-catch，如果子事务异常尝试捕获
        //note:会出现Transaction rolled back because it has been marked as rollback-onl，意思标记这个事务只能回滚无法提交
        try {
            orderItemService.saveBatchWithRequire(orderItemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //note:Required隔离级别下，父方法出现异常，两个方法同时回滚.
//        System.out.println(3/0);
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
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为REQUIRES_NEW，开启新的事务，二者不干扰
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        try {
            orderItemService.saveBatchWithRequestNew(orderItemList);
        } catch (Exception e) {
            log.error("子方法异常：{}", e.getMessage());
        }
        //note:模拟父异常的出现,出现父异常，子方法不会回滚，父方法回滚
//        System.out.println(3/0);
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
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithSupport(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        OrderEntity orderEntity = modelMapper.map(orderVO, OrderEntity.class);
        baseMapper.insert(orderEntity);
        orderVO.setOrderId(orderEntity.getOrderId());
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithSupports(orderItemList);
        //note:外部异常，如果当前上下文存在事务子方法以事务方法执行，因此夫方法异常子方法回滚。
        System.out.println(3/0);
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
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为NOT_SUPPORTED,挂起当前事务并以非事务方式执行,也就是子事务的异常不会回滚子事务，但会回滚父事务
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithNotSupported(orderItemList);
        //note:外部异常，NOT_SUPPORTED不支持事务，会挂起上下文的，因此仅回滚父
        System.out.println(3/0);
    }

    /**
     * 当前上下文存在事务则加入,不存在则报错
     * 由于父方法不存在事务，因此成功插入，而子方法直接报错了
     * @param orderVO
     */
    @Override
//    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithMandatory(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //外部调用,注解生效,使用传播方式为MANDATORY,加入当前事务
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
        //内部调用，事务注解不会生效
        insertOrderVO(orderVO);
        //note:传播方式为NEVER,如果上下文存在事务直接报错
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithNever(orderItemList);
    }

    /**
     * 如果上下存在事务，则开启一个嵌套驶入，父方法异常，二者都会滚，子方法异常只回滚子方法
     * @param orderVO
     *
     */
    //note:当上下文存在事务时，外层事务异常回滚会同时回滚内层事务;如果内层方法异常，在外部捕获的话，外层就不回滚，而REQUIRED不能catch
    //note：当上下文不存在事务，会新建一个事务
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithNested(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        insertOrderVO(orderVO);
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        //note:测试子任务异常和REQUIRED的区别，这个手动捕获就没事了，默认的即使捕获主方法仍回滚
        try {
            orderItemService.saveBatchWithNested(orderItemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //note:模拟异常的出现，外部方法异常，二者都回滚
//        System.out.println(3/0);
    }

    /**
     * 创建订单，内部调用事务同步管理器。注意同步管理器需要事务机制
     * @param orderVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void createOrderWithTransactionSyncManager(OrderVO orderVO) {
        if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
            throw new MissParamException("订单或订单明细");
        }
        insertOrderVO(orderVO);
        List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
        orderItemService.saveBatchWithNested(orderItemList);


        //note:使用afterCommit钩子，事务提交之后执行这个操作
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
//                TransactionSynchronization.super.afterCommit();
                log.info("订单创建成功,尝试发送邮件..........to:xxxxxxxxxx@163.com");
            }
        });
    }


    @Resource
    private PlatformTransactionManager platformTransactionManager;
    @Override
    public void createOrderWithPlatformTxManager(OrderVO orderVO) {
        //生命事务定义
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //设置隔离级别为读已提交
        transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //设置传播行为为REQUIRED
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            //note:业务代码
            if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
                throw new MissParamException("订单或订单明细");
            }
            insertOrderVO(orderVO);
            List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
            orderItemService.saveBatchWithNested(orderItemList);
            //note:提交事务
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            e.printStackTrace();
            //note:回滚事务
            platformTransactionManager.rollback(transactionStatus);
        }
    }

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * doInTransactionWithoutResult方法，执行一个无返回值的事务操作
     * @param orderVO
     */
    @Override
    public void createOrderWithTxTemplate(OrderVO orderVO) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                if (Objects.isNull(orderVO)|| CollUtil.isEmpty(orderVO.getOrderItemList())){
                    throw new MissParamException("订单或订单明细");
                }
                try {
                    insertOrderVO(orderVO);
                    List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
                    orderItemService.saveBatchWithNested(orderItemList);
                } catch (Exception e) {
                    //标记事务回滚
                    status.setRollbackOnly();
                    throw e;
                }
            }
        });
    }

    /**
     * doInTransaction方法，执行一个有返回值的事务操作
     * @param orderVO
     */
    @Override
    public void createOrderWithTxTemplateCallBack(OrderVO orderVO) {
        //直接通过事务模板设置一些事务属性
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //拿到事务执行后的返回结果
        OrderVO result = transactionTemplate.execute(new TransactionCallback<OrderVO>() {
            @Override
            public OrderVO doInTransaction(TransactionStatus status) {
                if (Objects.isNull(orderVO) || CollUtil.isEmpty(orderVO.getOrderItemList())) {
                    throw new MissParamException("订单或订单明细");
                }
                insertOrderVO(orderVO);
                List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
                orderItemService.saveBatchWithNested(orderItemList);
                return orderVO;
            }
        });
    }
    //note:lambda表达式简化上述方法的实现
    public void createOrderWithTxTemplateCallBack2(OrderVO orderVO) {
        //直接通过事务模板设置一些事务属性
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        //拿到事务执行后的返回结果
        OrderVO orderResult = transactionTemplate.execute(status -> {
            if (Objects.isNull(orderVO) || CollUtil.isEmpty(orderVO.getOrderItemList())) {
                throw new MissParamException("订单或订单明细");
            }
            insertOrderVO(orderVO);
            List<OrderItemVO> orderItemList = orderVO.getOrderItemList();
            orderItemService.saveBatchWithNested(orderItemList);
            return orderVO;
        });
    }


}
