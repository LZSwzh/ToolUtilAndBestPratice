package com.best.practice.version.juc.b_concurrent_principle.volatile_principle;

/**https://www.cnblogs.com/myseries/p/12699947.html
 * 介绍CPU缓存机制中CacheLine
 */
public class CpuCacheIneffective {

    public static void main(String[] args) {
        /**分别横向打印和纵向打印二维数组并分析效率*/
         /*
          横向打印耗时: Loop times: 30ms
          纵向打印耗时: Loop times: 88ms
          横向打印时:
               long占据8个字节，一行占8*8=64个字节，刚好是一个缓冲行(CacheLine)的大小。
               当i=0,j=0的时候,由于cache是以缓冲行(CacheLine)为基本单位的,所以会将[0][0]-[0][7]的数据加载到cache中
               当i=1,j=0的时候也同理......
          纵向打印时：
               由于破坏了空间的连续性，大大降低了缓存命中的概率，因此效率低。
         */
        printArrWithDiffMethod(1);
        printArrWithDiffMethod(2);
    }

    public static void printArrWithDiffMethod(int type){
        long[][] arr = new long[1024 * 1024][8];
        long marked = System.currentTimeMillis();
        String typeStr = (1==type)?"横向:":"纵向:";
        int sum = 0;
        if (1==type){
            // 横向遍历
            for (int i = 0; i < 1024 * 1024; i += 1) {
                for (int j = 0; j < 8; j++) {
                    sum += arr[i][j];
                }
            }
            System.out.println(typeStr+"  Loop times:" + (System.currentTimeMillis() - marked)+ "ms");
        }else {
            // 纵向遍历
            for (int i = 0; i < 8; i += 1) {
                for (int j = 0; j < 1024 * 1024; j++) {
                    sum += arr[j][i];
                }
            }
            System.out.println(typeStr+"  Loop times:" + (System.currentTimeMillis() - marked)+ "ms");
        }



    }

    public static void printCacheLineWidth(){
        int[] array = new int[1024];
        long startTime, endTime;
        int iterations = 1000000;

        // 测试缓存行大小
        for (int size = 1; size < 1024; size *= 2) {
            startTime = System.nanoTime();
            for (int i = 0; i < iterations; i++) {
                for (int j = 0; j < array.length; j += size) {
                    array[j]++;
                }
            }
            endTime = System.nanoTime();
            System.out.println("Time: " + (endTime - startTime)+"，Size: " + size );
        }
    }
}
