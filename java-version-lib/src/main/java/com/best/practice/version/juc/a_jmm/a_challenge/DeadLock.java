package com.best.practice.version.juc.a_jmm.a_challenge;

/**
 * 死锁的四个条件
 *  1.资源互斥：资源是互斥的无法被多个线程同时使用
 *  2.请求和保持：每个线程都持有资源并申请另外的资源
 *  3.不可剥夺：资源在使用完毕前无法被其他线程剥夺
 *  4.循环等待：每个线程都在申请其他线程持有的资源构成一个循环
 * 避免死锁的方法：
 * - 避免一个线程占用多个锁
 * - 避免一个线程在一个锁内占用多个资源
 * - 尝试用tryLock(timeout)定时锁代替内部锁机制
 * - 对于数据库锁，加锁和解锁必须放到一个连接中，否则可能解锁失败
 */
public class DeadLock {

    private static final String resourceA = "a";

    private static final String resourceB = "b";

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (resourceA) {
                //锁住A后休眠1s让B先持有B
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + "obtain resourceB");
                }
            }
        });
        t1.setName("thread-A");
        t1.start();
        Thread t2 = new Thread(() -> {
            synchronized (resourceB) {
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + "obtain resourceA");
                }
            }
        });
        t2.setName("thread-B");
        t2.start();

    }
}
