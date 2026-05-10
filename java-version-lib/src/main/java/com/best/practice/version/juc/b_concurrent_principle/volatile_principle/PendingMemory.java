package com.best.practice.version.juc.b_concurrent_principle.volatile_principle;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

/**
 * 在FakeSharing的基础上，通过手动对齐缓存行，来提高性能.平均耗时:926350ms，观察不出明显的变化为什么
 * 优化前代码耗时       ： 62038534000ms
 * 将如下代码运行一次耗时:  28958268000ms
 * 优化效率基本是一杯了......
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
        //对象头(12)+name引用(4)+salary引用(4)=20；补了5个long+1个Object凑够64字节
        System.out.println("========= Static Print PendingUser Size："+size+" byte ========");
        System.out.println("========= Static Print Clazz Object Layout Begin========");
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        /*内存布局:对象头12(mark8+clazz4)+name引用4+salary引用4+(5*8+4)对齐=64byte
          0   8                    (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
          8   4                    (object header: class)    0x01003418
         12   4   java.lang.String VolatileUser.name         (object)
         16   4     java.lang.Long VolatileUser.salary       10000
         20   4   java.lang.Object PendingUser.p6            null
         24   8               long PendingUser.p1            0
         32   8               long PendingUser.p2            0
         40   8               long PendingUser.p3            0
         48   8               long PendingUser.p4            0
         56   8               long PendingUser.p5            0
        Instance size: 64 bytes
        Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
        */
        System.out.println("========= Static Print Clazz Object Layout End========");
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
            threads[threadNum].start();
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