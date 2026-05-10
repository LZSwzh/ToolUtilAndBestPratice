package com.best.practice.transaction.util;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 工具类,封装了
 */
public class TxSyncUtil {
    /**
     * note: 事务成功提交后执行
     * @param task
     */
    public static void runAfterCommit(Runnable task) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            task.run();                       // 当前无事务，直接跑
            return;
        }
        //调方法抛出的异常不会导致事务回滚,可能需要自己try-catch处理
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                task.run();
            }
        });
    }

    /**
     * note:运行在事务完成之后，不管是回滚还是提交
     * @param task
     */
    public static void runAfterCompletion(Runnable task) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) {
            task.run();                       // 当前无事务，直接跑
            return;
        }
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                //回滚的状态
                if (status == STATUS_ROLLED_BACK) {


                }//提交的状态
                else if (status == STATUS_COMMITTED){

                }
                task.run();
            }
        });
    }
}
