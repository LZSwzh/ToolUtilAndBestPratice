package com.best.pratice.java_version.jdk9.inter;

/**
 * JDK8已经引入了默认方法，主要是为了向后兼容，即给接口添加新的功能但是不影响其实现类。
 *
 * JDK9新增了私有方法，为了提高接口内部代码的复用性。
 *
 */
public interface MyInterface {
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
