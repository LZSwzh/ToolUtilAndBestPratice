package com.best.pratice.mutithread.orderControl;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ControlBuCompletableFuture {
    public static void main(String[] args) {
        //CompletableFuture
        //执行任务的方法：  runAsync:无返回值   supplyAsync:有返回值
        //任务回调        whenComplete:无返回值   handler:有返回值
        //任务组合        allOf:全部执行完再执行    anyOf：有一个执行完再执行
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            return "目录1UUID";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(task1.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            return "目录2UUID";
        });
        //等待任务1，2执行完毕后执行
        CompletableFuture<String> task3 = CompletableFuture.allOf(task1, task2).handle((res, throwable) -> {
            try {
                //alyyOf已经保证了等待两个任务执行完毕
                System.out.println(task2.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            return "目录3UUID";
        });
        String finalRes = task3.join();
        System.out.println(finalRes);
    }
}
