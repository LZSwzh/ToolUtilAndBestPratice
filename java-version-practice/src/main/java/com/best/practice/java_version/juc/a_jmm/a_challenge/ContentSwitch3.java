package com.best.practice.java_version.juc.a_jmm.a_challenge;

import cn.hutool.core.date.TimeInterval;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 这里用Thread.sleep模拟耗时的网络请求;发现其实对于这种任务耗时较久的，使用多线程效果还行，但是如果任务耗时较短，多线程反而会增加开销
 * 当网络延时:
 * 200ms     串行414ms  并行213ms    (213*2)-414=12ms
 * 500ms     串行1027ms  并行529ms   (529*2)-1027=31ms
 * 1000ms    串行2023ms  并行1015ms  (1015*2)-2023=7ms
 */
public class ContentSwitch3 {
    public static final long delay = 200;
//    public static final long delay = 500;
//    public static final long delay = 1000;
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        serial();
        concurrent();
    }

    public static void serial(){
        final TimeInterval timer = new TimeInterval();
        timer.start();
        System.out.println("===========================Serial Begin==========================");
        try {
            System.out.println("Serial taskA 开始执行");
            int resA = Math.random()>0.5?1:0;
            Thread.sleep(delay);
            System.out.println("Serial taskA 执行完毕");


            System.out.println("Serial taskB 开始执行");
            int resB = Math.random()>0.5?1:0;
            Thread.sleep(delay);
            System.out.println("Serial taskB 执行完毕");

            System.out.println("Concurrent 两个异步任务的结果之和为: " + (resA+ resB));

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("===========================Serial End==========================");
        timer.interval();
        System.out.println("Timer A took {"+timer.intervalMs()+"} ms");
    }

    public static void concurrent() throws InterruptedException, ExecutionException {
        final TimeInterval timer = new TimeInterval();
        timer.start();
        System.out.println("===========================Concurrent Begin==========================");
        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(() -> {
            System.out.println("Concurrent futureA 开始执行");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                System.out.println("Concurrent futureA 被中断");
                throw new RuntimeException(e);
            }
            System.out.println("Concurrent futureA 执行完毕");
            return Math.random()>0.5?1:0;
        });
        CompletableFuture<Integer> futureB = CompletableFuture.supplyAsync(() -> {
            System.out.println("Concurrent futureB 开始执行");
            try {
                Thread.sleep(delay);
            }catch (InterruptedException e) {
                System.out.println("Concurrent futureB 被中断");
                throw new RuntimeException(e);
            }
            System.out.println("Concurrent futureB 执行完毕");
            return Math.random()>0.5?1:0;
        });

        CompletableFuture.allOf(futureA,futureB).thenRun(()->{
            try {
                int sum = futureA.get() + futureB.get();
                System.out.println("Concurrent 两个异步任务的结果之和为: " + sum);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();
        System.out.println("===========================Concurrent End==========================");
        timer.interval();
        System.out.println("Timer B took {"+timer.intervalMs()+"} ms");
    }
}
