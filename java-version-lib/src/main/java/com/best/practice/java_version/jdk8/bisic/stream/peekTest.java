package com.best.practice.java_version.jdk8.bisic.stream;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 介绍lambda的peek方法
 * 1. 一个中间操作，必须跟一个终端操作。
 * 2. 对流中的每个元素执行一个操作（如打印、记录日志等），但不会改变流中元素本身
 * 3. peek方法返回的流与原始流是同一个对象
 */
public class peekTest {
    public static void main(String[] args) {
        /**
         * 1.没有终端操作不生效
         */
        List<String> list = List.of("1", "2", "3", "4", "5");
        list.stream().peek(name -> System.out.println(name));
        /**
         * 2.常用于调试，打印日志等
         */
        List<Integer> list2 = List.of(323, 4, 60, 0123, 452, 34);
        list2.stream()
                .peek(n->System.out.println("---------------------------"))
                .peek(n->System.out.println("peek1:"+n))
                .filter(n->n%2==0)
                .peek(n->System.out.println("peek2:"+n))
                .map(n->n*2)
                .peek(n->System.out.println("peek3:"+n))
                .peek(n->System.out.println("---------------------------"))
                .collect(Collectors.toList());
        /**
         * 3.可以用来在peek中向外部集合添加元素
         */
//        Lists.
        LinkedList<String> pendingDelList = new LinkedList<>();
        List.of("1", "2", "3", "4", "5");
        list.stream().peek(pendingDelList::add).collect(Collectors.toList());
        System.out.println("pendingDelList"+pendingDelList);
    }
}
