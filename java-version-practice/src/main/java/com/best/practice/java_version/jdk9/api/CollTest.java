package com.best.practice.java_version.jdk9.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollTest {
    public static void main(String[] args) {
        /**
         * 新增了一些工厂方法,注意这里创建的是‘不可变集合’，一旦对齐增删改查会报错：
         *      java.lang.UnsupportedOperationException异常
         */
        List<String> list1 = List.of("1", "2", "3", "4");
        Set<String> set1 = Set.of("3", "4", "5", "7");
        Map<String, String> map1 = Map.of("k1", "v1", "k2", "v2", "k3", "v3");
    }
}
