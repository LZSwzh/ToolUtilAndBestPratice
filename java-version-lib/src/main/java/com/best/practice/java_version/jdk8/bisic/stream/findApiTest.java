package com.best.practice.java_version.jdk8.bisic.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class findApiTest {
    public static void main(String[] args) {
        List<Integer> nums = List.of(510, 89, 23, 467, 89, 516, 2);
        Optional<Integer> opt = nums.stream().sorted(Comparator.reverseOrder()).findFirst();
        System.out.println("排序后的第一个元素是:"+opt.get());
        Optional<Integer> opt2 = nums.stream().filter(n -> n > 100).findAny();
        System.out.println("找到任意一个大于100的元素:"+opt2.get());
    }
}
