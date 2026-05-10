package com.best.practice.version.jdk8.bisic.funcInterface;

import java.util.function.Consumer;

public class ConsumerFun {
    public static void main(String[] args) {
        Consumer<String> myConsumer1 = (t) -> System.out.println("myConsumer1："+t);
        //消费者接口的使用accept
        myConsumer1.accept("hello accept");
        System.out.println("-------------------------------------------------------");
        //and的使用
        Consumer<String> myConsumer2 = myConsumer1.andThen((t -> System.out.println("myConsumer2：" + t)));
        myConsumer2.accept("hello andThen");
    }
}
