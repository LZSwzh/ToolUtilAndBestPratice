package com.best.practice.java_version.juc.b_concurrent_principle.volatile_principle;

import org.apache.lucene.util.RamUsageEstimator;

/**
 * 在FakeSharing的基础上，通过手动对齐缓存行，来提高性能.平均耗时:926350ms，观察不出明显的变化为什么
 * 945500ms
 * 835800ms
 * 973400ms
 * 777600ms
 * 712200ms
 * 1007300ms
 * 1037200ms
 * 880500ms
 * 951300ms
 * 1142700ms
 */
public class PendingMemory {
    private static final int ROWS = 1024;
    private static final int COLS = 1024;
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    public final static long ITERATIONS = 500L * 1000L * 1000L;

    /**
     * 创建一个List结构用于存放User，其中user的salary使用volatile修饰
     */
    public static PendingUser[] userList = new PendingUser[NUM_THREADS];

    static {
        PendingUser user = new PendingUser("张三", Long.valueOf("10000"));
        long size = RamUsageEstimator.shallowSizeOf(user);
        //对象头(12)+name引用(4)+salary引用(4)=20；补了5个long凑够64字节
        System.out.println("========= Static Print PendingUser Size："+size+" byte ========");
        for (int i = 0; i < userList.length; i++) {
            userList[i] = new PendingUser();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long begin = System.nanoTime();
        Thread[] threads = new Thread[NUM_THREADS];
        for (int threadNum = 0; threadNum < threads.length; threadNum++) {
            final int idx = threadNum;
            threads[threadNum] = new Thread(() -> {
                long j = ITERATIONS + 1;
                while (0 != --j) {
                    PendingUser user = userList[idx];
                    user.setSalary(j);
                }
            });
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("伪共享代码业务逻辑执行完毕,耗时:"+(System.nanoTime()-begin)+"ms");
    }

    static class PendingUser extends VolatileUser{
        private long p1,p2,p3,p4,p5;
        private Object p6;
        public PendingUser() {
            super();
        }
        public PendingUser(String name, Long salary) {
            super(name, salary);
        }
    }
    static class VolatileUser{
        private String name;
        private volatile Long salary;
        public VolatileUser() {}

        public VolatileUser(String name, Long salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getSalary() {
            return salary;
        }

        public void setSalary(Long salary) {
            this.salary = salary;
        }
    }
}