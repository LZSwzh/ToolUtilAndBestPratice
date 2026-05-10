package com.best.practice.version.jdk8.bisic.funcInterface;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionFun {
    public static void main(String[] args) {
        /**
         * Function<T,R>:传入一个T类型，返回一个R类型
         */
        /**
         * 1.1.andThen和compose:前者先执行当前的后执行传入的；后者先执行传入的后执行当前的。
         */
        Function<Integer,Integer> myFunction1 = (x1)-> {
            return x1*2;
        };
        System.out.println("------------------- R apply(T)              :"+myFunction1.apply(2));
        Function<Integer, Integer> myFunction2 = myFunction1.andThen((x2) -> {
            return x2 + 100;
        });
        System.out.println("------------------- F andThen(function)     :"+myFunction2.apply(2));

        //compose方法 函数组合
        Function<Integer, Integer> myFunction3 = myFunction1.compose((x3) -> {return x3 + 100;});
        System.out.println("------------------- F compose(function)     :"+myFunction3.apply(2));
        /**
         * 1.2.identity:返回一个函数，该函数返回其输入参数;基本上等价于t->t
         */
        System.out.println("------------------- F<T,T> identity()       :"+Function.identity().apply(2));

        List<User> users = List.of(new User("张三", 18), new User("李四", 18), new User("王五", 20), new User("赵六", 20));
        Map<Integer, User> userMapByAge = users.stream().collect(Collectors.toMap(User::getAge, Function.identity(), (key1, key2) -> key1));
        System.out.println("------------------- F<T,T> identity with map:"+userMapByAge);


    }

    static class  User{
        private String name;
        private Integer age;

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

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
