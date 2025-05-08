package com.best.practice.java_version.jdk8.bisic;

import java.util.List;

/**
 * 介绍Lombok的peek方法
 * 1. 作用：
 *      1.1 用于链式调用，在链式调用中对对象进行操作，返回对象本身
 *      1.2 用于调试，在链式调用中对对象进行操作，返回对象本身，方便调试
 */
public class peekTest {
    public static void main(String[] args) {
        List<String> list = List.of("1", "2", "3", "4", "5");
        list.stream().peek(name -> System.out.println(name))
                     .forEach(name -> System.out.println(name));
    }
}
