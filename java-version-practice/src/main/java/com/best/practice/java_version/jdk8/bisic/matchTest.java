package com.best.practice.java_version.jdk8.bisic;

import java.util.List;

public class matchTest {
    public static void main(String[] args) {
        List<String> names = List.of("admin", "smith", "tom", "Jelly");
        //anyMatch,任意一个元素满足条件返回true;类似Javascript的some方法
        boolean anyMatch = names.stream().anyMatch(name -> name.equalsIgnoreCase("admin"));
        System.out.println("anyMatch："+anyMatch);
        //allMatch,所有元素满足条件返回true;类似javascript的every方法
        boolean allMatch = names.stream().allMatch(name -> name.equalsIgnoreCase("admin"));
        System.out.println("allMatch:"+allMatch);
        //noneMatch,所有元素都不满足条件返回true
        boolean noneMatch = names.stream().noneMatch(name -> name.equalsIgnoreCase("admin"));
        System.out.println("noneMatch:"+noneMatch);
    }
}
