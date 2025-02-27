package com.best.pratice.mutithread.orderControl;

import java.util.concurrent.*;

/**
 * 使用Future控制线程池中线程的执行顺序
 */
public class ControlByFuture {
    public static void main(String[] args) {
        //TODO 不推荐这样使用线程池，这里只是用作示范
        ExecutorService excutor = Executors.newFixedThreadPool(3);

        //生成目录1
        final Future<String> taskOneRes = excutor.submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(2000);//模拟网络请求耗时
                return  "目录1的UUID";//返回创建Ones目录后生成的目录UUID
            }
        });
        //生成目录2，要求目录一创建后创建目录2
        final Future<String> taskTwoRes = excutor.submit(new Callable<String>() {
            public String call() throws Exception {
                String oneDirUUID = taskOneRes.get();
                Thread.sleep(1000);//模拟网络请求耗时
                System.out.println(oneDirUUID);
                return "目录2的UUID";//返回创建Ones目录后生成的目录UUID
            }
        });
        //生成目录3，要求目录2创建后创建目3
        Future<String> taskThreeRes = excutor.submit(new Callable<String>() {
            public String call() throws Exception {
                String twoDirUUID = taskTwoRes.get();
                Thread.sleep(1000);//模拟网络请求耗时
                System.out.println(twoDirUUID);
                return "目录3的UUID";//返回创建Ones目录后生成的目录UUID
            }
        });
        //打印最终结果
        try {
            System.out.println(taskThreeRes.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
