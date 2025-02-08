package com.best.pratice.mutithread.juc;

/**
 * https://www.cnblogs.com/little-fly/p/14259314.html
 * @author wangzh
 */
public class CacheLinePadding {
    /**
     * 1.缓存行
     * JVM缓存是由多个缓存行组成的，以缓存行为基本单位，一个缓存行的大小一般为64字节
     * 2.伪共享
     * 当不同的线程在操作两份不同的数据时，如果这两份数据刚好位于同一个缓存行中，那么彼此之间就会互相影响
     * 3.缓存行对齐
     *
     */
}
