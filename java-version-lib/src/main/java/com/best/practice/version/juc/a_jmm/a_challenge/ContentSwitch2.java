package com.best.practice.version.juc.a_jmm.a_challenge;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 并发一定比串行执行的顺序快？答案是否定的
 * 10000L(一万次):串行13ms  并行13ms
 * 1000000L(百万次):串行20ms  并行21ms
 * 100000000L(一亿次):串行19ms  并行13ms
 * 1000000000L(10亿次):串行18ms 并行16ms
 * 2000000000L(50亿次):串行18ms 并行15ms
 */
public class ContentSwitch2 {

    private static final long count = 2000000000L;

    public static void main(String[] args) {
        serial();
        concurrent();
    }
    public static void serial(){
        long begin = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }
        int b=0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        System.out.println("serial a：" + a);
        System.out.println("serial b：" + b);
        System.out.println("serial 耗时:"+(System.currentTimeMillis()-begin));
    }
    public static void concurrent(){
        long begin = System.currentTimeMillis();

        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> {
            int a = 0;
            for (int i = 0; i < count; i++) {
                a += 5;
            }
//            System.out.println("concurrent a：" + a);
            return a;
        });

        CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
            int b = 0;
            for (int i = 0; i < count; i++) {
                b--;
            }
//            System.out.println("concurrent b：" + b);
            return b;
        });
        CompletableFuture.allOf(futureA,futureB)
                .thenRun(()->{
                    try {
                        int resultA = futureA.get();
                        int resultB = futureB.get();
                        System.out.println("concurrent a: " + resultA);
                        System.out.println("concurrent b: " + resultB);
                        System.out.println("concurrent 执行耗时: " + (System.currentTimeMillis() - begin) + " 毫秒");
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                }).join();

    }

}
