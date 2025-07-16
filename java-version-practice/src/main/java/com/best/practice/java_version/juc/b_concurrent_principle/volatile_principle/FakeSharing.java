package com.best.practice.java_version.juc.b_concurrent_principle.volatile_principle;

import com.best.practice.java_version.jdk8.entity.User;
import org.apache.lucene.util.RamUsageEstimator;
import org.openjdk.jol.info.ClassLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  * https://blog.csdn.net/MrYushiwen/article/details/123171635
 *  * 通过对齐缓存行的宽度优化volatile
 *  将如下代码运行一次耗时:
 *  62038534000ms、
 */
public class FakeSharing {
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
        //对象头(12)+name引用(4)+salary引用(4)=20；对象还会对齐到8的倍数，所以size=24;
        long size = RamUsageEstimator.shallowSizeOf(user);
        System.out.println("========= Static Print VolatileUser Size："+size+" byte ========");
        System.out.println("========= Static Print Clazz Object Layout Begin========");
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        /*内存布局:对象头12(mark8+clazz4)+name引用4+salary引用4+对齐填充4=24byte
        OFF  SZ               TYPE DESCRIPTION               VALUE
          0   8                    (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
          8   4                    (object header: class)    0x010031f8
         12   4   java.lang.String VolatileUser.name         (object)
         16   4     java.lang.Long VolatileUser.salary       10000
         20   4                    (object alignment gap)
        Instance size: 24 bytes
        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
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
