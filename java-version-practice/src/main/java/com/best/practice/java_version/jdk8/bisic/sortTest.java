package com.best.practice.java_version.jdk8.bisic;

import java.util.Comparator;
import java.util.List;

public class sortTest {
    public static void main(String[] args) {
        People zhangsan = new People("zhangsan", 23, 1000);
        People lisi = new People("lisi", 18, 13000);
        People wangwu = new People("wangwu", 34, 20000);
        People zhaoliu = new People("zhaoliu", 34, 25000);
        People tom = new People("tom", 37, 20000);
        List<People> peoples = List.of(tom,zhangsan, lisi, wangwu, zhaoliu);
        /** 按照单个字段排序 */
        //默认按照升序排序
        System.out.println("============================1.1.根据薪资,默认升序排序==============================");
        peoples.stream().sorted(Comparator.comparing(People::getSalary)).forEach(t-> System.out.println(t));
        //可以使用如下发方法变更顺序
        System.out.println("============================1.2.根据薪资,使用Comparator.reverseOrder()倒序排序==============================");
        peoples.stream().sorted(Comparator.comparing(People::getSalary,Comparator.reverseOrder())).forEach(t-> System.out.println(t));
        System.out.println("============================1.3.根据薪资,使用.reversed()倒序排序==============================");
        peoples.stream().sorted(Comparator.comparing(People::getSalary).reversed()).forEach(t-> System.out.println(t));
        System.out.println("============================1.4.数值比较更推荐特定方法减少装箱拆箱开销  ==============================");
        peoples.stream().sorted(Comparator.comparingInt(People::getSalary).reversed()).forEach(System.out::println);
        /** 多字段排序 */
        System.out.println("============================1.5.根据薪资降序,年龄升序排序,使用.thenComparing  ==============================");
        peoples.stream().sorted(Comparator.comparing(People::getSalary,Comparator.reverseOrder())
                .thenComparingInt(People::getAge)).forEach(System.out::println);


    }

    static class People{
        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }

        private String name;
        private Integer age;
        private Integer salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getSalary() {
            return salary;
        }

        public void setSalary(Integer salary) {
            this.salary = salary;
        }

        public People(String name, Integer age, Integer salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
    }
}
