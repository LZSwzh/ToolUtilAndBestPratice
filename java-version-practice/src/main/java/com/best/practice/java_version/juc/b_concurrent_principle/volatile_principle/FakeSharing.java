package com.best.practice.java_version.juc.b_concurrent_principle.volatile_principle;

/**
 *  * https://blog.csdn.net/MrYushiwen/article/details/123171635
 *  * 通过对齐缓存行的宽度优化volatile
 *  存在为伪共享问题的代码执行五次的结果如下，平均耗时:12128160ms
 *  6625300ms
 *  9936200ms
 *  15331500ms
 *  11321800ms
 *  18445000ms
 */
public class FakeSharing {
    private static final int ROWS = 1024;
    private static final int COLS = 1024;
    private static final int NUM_THREADS = 4;
    private static final int CACHE_LINE_SIZE = 64; // 假设缓存行大小为64字节

    // 二维数组，每个元素为long类型，占用8字节，每行的每8个元素占用一个缓存行
    public static long[][] matrix = new long[ROWS][COLS];

    public static void main(String[] args) throws InterruptedException {
        long begin = System.nanoTime();
        Thread[] threads = new Thread[NUM_THREADS];

        // 初始化数组
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = 0;
            }
        }

        // 创建并启动线程
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < COLS; j++) {
                    // 每个线程更新同一列的不同行,导致同个缓存行内的数据被多个线程同时修改导致伪共享
                    int row = j * NUM_THREADS + threadId;
                    if (row<ROWS) matrix[row][j] = 1; // 更新操作
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("伪共享代码业务逻辑执行完毕,耗时:"+(System.nanoTime()-begin)+"ms");

        // 验证结果
//        for (int j = 0; j < COLS; j++) {
//            boolean allSet = true;
//            for (int i = 0; i < ROWS; i++) {
//                if (matrix[i][j] != 1) {
//                    allSet = false;
//                    break;
//                }
//            }
//            if (!allSet) {
//                System.out.println("Column " + j + " is not fully set.");
//            }
//        }
    }
}
