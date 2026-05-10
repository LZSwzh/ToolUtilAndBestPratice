package com.best.practice.version.jvm;

/**
 * @author wuwuw630
 */
public class JvmMemoryTest {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("JVM获取的最大内存 :"+ formatMemory(runtime.maxMemory()));
        System.out.println("JVM已经使用的内存 :"+ formatMemory(runtime.totalMemory()));
        System.out.println("JVM还未使用的内存 :"+ formatMemory(runtime.freeMemory()));

    }

    public static String formatMemory(long memory){
        return memory/1024/1024+"M";
    }
}
