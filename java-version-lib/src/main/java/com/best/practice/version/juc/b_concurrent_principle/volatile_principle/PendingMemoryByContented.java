package com.best.practice.version.juc.b_concurrent_principle.volatile_principle;

import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;


/**
 * 在FakeSharing的基础上，通过注解对齐。在JDK9及以上通过--XX:-RestrictContended开启；-XX:ContendedPaddingWidth设置填充宽度
 * JDK9以上由于启用了JPMS,因此需要在IDEA的java compile中添加--add-exports java.base/jdk.internal.vm.annotation=ALL-UNNAMED
 * 改了一些配置后三者的执行时间
 * 优化前代码耗时       ： 62038534000ms
 * 手动对齐内存耗时     :  19012375101ms
 * 使用注解对齐内存     ： 16287798200ms，不写ContendedPaddingWidth打印出的对象的大小是152
 * 其实后面两个的性能差不多，只是每次执行的效果不太一样
 */
public class PendingMemoryByContented {
    private static final int ROWS = 1024;
    private static final int COLS = 1024;
    private static final int NUM_THREADS = Runtime.getRuntime().availableProcessors();
    public final static long ITERATIONS = 500L * 1000L * 1000L;

    /**
     * 创建一个List结构用于存放User，其中user的salary使用volatile修饰
     */
    public static VolatileUser[] userList = new VolatileUser[NUM_THREADS];

    static {
        VolatileUser user = new VolatileUser("张三", Long.valueOf("10000"));
        long size = RamUsageEstimator.shallowSizeOf(user);
        //对象头(12)+name引用(4)+salary引用(4)=20；对象还会对齐到8的倍数，所以size=24;
        System.out.println("========= Static Print ContentedUser Size："+size+" byte ========");
        System.out.println("========= Static Print Clazz Object Layout Begin========");
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        /*内存布局:默认在对象/字段前后添加128字节的padding,大于多数硬件缓存行大小,避免
        OFF  SZ               TYPE DESCRIPTION               VALUE
          0   8                    (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
          8   4                    (object header: class)    0x010031f8
         12   4   java.lang.String VolatileUser.name         (object)
         16 128                    (alignment/padding gap)
        144   4     java.lang.Long VolatileUser.salary       10000
        148   4                    (object alignment gap)
        Instance size: 152 bytes
        Space losses: 128 bytes internal + 4 bytes external = 132 bytes total
        */
        System.out.println("========= Static Print Clazz Object Layout End========");
        for (int i = 0; i < userList.length; i++) {
            userList[i] = new VolatileUser();
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
                    VolatileUser user = userList[idx];
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

    static class VolatileUser{
        private String name;
//        @jdk.internal.vm.annotation.Contended
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