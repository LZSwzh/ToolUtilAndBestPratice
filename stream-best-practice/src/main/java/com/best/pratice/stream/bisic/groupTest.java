package com.best.pratice.stream.bisic;

import java.util.*;

public class groupTest {
    public static void main(String[] args) {
        Person p1 = new Person(1, 1, "张三");
        Person p2 = new Person(1, 1, "张四");
        Person p3 = new Person(1, 2, "李三");
        Person p4 = new Person(1, 2, "李四");
        Person p5 = new Person(1, 2, "王三");
        Person p6 = new Person(1, 3, "王四");
        List<Person> data = Arrays.asList(p1, p2, p3, p4, p5, p6);

        HashMap<Integer, Map<Integer, List<Person>>> groupData = new HashMap<>();
        data.forEach(item -> {
            // 使用 item.num 而不是 p1.num
            Map<Integer, List<Person>> subData = groupData.computeIfAbsent(item.num, k -> new HashMap<>());
            List<Person> personList = subData.computeIfAbsent(item.age, k -> new LinkedList<>());
            personList.add(item);
        });

        // 打印 groupData
        groupData.forEach((num, subMap) -> {
            System.out.println("Num: " + num);
            subMap.forEach((age, personList) -> {
                System.out.println("  Age: " + age);
                personList.forEach(person -> System.out.println("    " + person.name));
            });
        });

    }

    static class Person{
        public Person(Integer num, Integer age, String name) {
            this.num = num;
            this.age = age;
            this.name = name;
        }

        private Integer num;

        private Integer age;

        private String name;
    }
}
