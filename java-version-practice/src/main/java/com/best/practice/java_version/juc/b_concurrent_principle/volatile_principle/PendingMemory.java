package com.best.practice.java_version.juc.b_concurrent_principle.volatile_principle;

/**
 * 在FakeSharing的基础上，通过手动对齐缓存行，来提高性能
 */
public class PendingMemory {
    private static final int ROWS = 1024;
    private static final int COLS = 1024;
    private static final int NUM_THREADS = 4;
    private static final int CACHE_LINE_SIZE = 64; // 假设缓存行大小为64字节

    // 缓存行填充类
    public static class PaddedLong {
        public volatile long value;
        private long[] padding = new long[(CACHE_LINE_SIZE / Long.BYTES) - 1];
        public PaddedLong() {
            this.value = 0;
        }
        public PaddedLong(long value) {
            this.value = value;
        }
    }

    // 二维数组，每个元素为PaddedLong类型
    public static PaddedLong[][] matrix = new PaddedLong[ROWS][COLS];

    public static void main(String[] args) throws InterruptedException {
        long begin = System.nanoTime();
        Thread[] threads = new Thread[NUM_THREADS];

        // 初始化数组
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = new PaddedLong();
            }
        }

        // 创建并启动线程
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < COLS; j++) {
                    // 每个线程更新同一列的不同行
                    int row = j * NUM_THREADS + threadId;
                    if (row<ROWS) matrix[row][j] = new PaddedLong(1); // 更新操作
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("手动对齐缓存行,结果耗时:"+(System.nanoTime()-begin));
    }
}