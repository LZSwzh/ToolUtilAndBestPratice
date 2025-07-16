package com.best.practice.java_version.juc.a_jmm.a_challenge;

/**
 * 并发一定比串行执行的顺序快？答案是否定的
 * 10000L(一万次):串行10ms  并行16ms
 * 1000000L(百万次):串行16ms  并行26ms
 * 100000000L(一亿次):串行26ms  并行11ms
 * 1000000000L(10亿次):串行19ms 并行28ms
 * 2000000000L(50亿次):串行
 */
public class ContentSwitch {
    public static final long count = 5000000000L;

    public static void main(String[] args) {
        executeTask("SERIAL");
        executeTask("CONCURRENT");
    }

     public static void executeTask(String mode){

         if ("SERIAL".equalsIgnoreCase(mode)){
            serial();
         } else if ("CONCURRENT".equalsIgnoreCase(mode)) {
             try {
                 concurrent();
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }
         }

     }

     private static void serial(){
        long begin = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
             a+=5;
        }
        int b=0;
         for (int i = 0; i < count; i++) {
             b--;
         }
         System.out.println("serial a:"+a);
         System.out.println("serial b:"+b);
         System.out.println("serial 代码执行耗时:"+(System.currentTimeMillis()-begin));
     }

     private static void concurrent() throws InterruptedException {
         long begin = System.currentTimeMillis();
         Thread t1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 int a = 0;
                 for (int i = 0; i < count; i++) {
                     a += 5;
                 }
                 System.out.println("concurrent a:" + a);
             }
         });
         t1.start();
         int b =0;
         for (int i = 0; i < count; i++) {
             b--;
         }
         t1.join();
         System.out.println("concurrent b:"+b);

         System.out.println("concurrent 执行耗时:"+(System.currentTimeMillis()-begin));
     }


}
