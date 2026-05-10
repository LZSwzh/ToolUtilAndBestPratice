package com.best.practice.version.jdk9.inter;

public interface InterTest {
    /**
     * JDK8支持的静态方法
     */
    static void myStaticFunc(){
        System.out.println("staticFunc......begin");
        System.out.println("staticFunc......end");
    }
    /**
     * JDK8支持的默认方法
     */
    default void myFunc1(){
        System.out.println("myFunc1......begin");
        myPrivateFunc();
        System.out.println("myFunc1......end");
    }
    default void myFunc2(){
        System.out.println("myFunc2......begin");
        myPrivateFunc();
        System.out.println("myFunc2......end");
    }


    /**
     * JDK9支持的私有方法
     */
    private void myPrivateFunc(){
        System.out.println("这是JDK9提供的私有方法");
    }
}
