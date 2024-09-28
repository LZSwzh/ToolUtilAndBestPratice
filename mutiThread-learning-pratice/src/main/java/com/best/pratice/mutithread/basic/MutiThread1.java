package com.best.pratice.mutithread.basic;


public class MutiThread1 {
    volatile int count = 100;

    public static void main(String[] args) {
        TaskByThread t1 = new TaskByThread();
        t1.start();

    }

    /**
     * 通过继承Thread实现多线程
     */
    static class TaskByThread extends Thread{
        int begin = 1;
        int end = 100;
        @Override
        public void run() {
            for (int i = begin; i < end; i++) {

            }
        }
    }


}

