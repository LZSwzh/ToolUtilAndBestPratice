package com.best.pratice.stream.nullIdex;

import com.best.pratice.stream.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class NullTest {

    public static User[] userArr = new User[10];

    /**
     * 构造数据
     */
    static {
        User user1 = new User("wzh", 232, 5222.2);
        userArr[0] = user1;
        User user2 = new User("yrt", 74, 70512985.0);
        userArr[1] = user2;
        User user3 = new User("qws", 722, 22342.2);
        userArr[2] = user3;
        User user4 = new User("uhg", 224, 2272.2);
        userArr[3] = user4;
    }
    public static void main(String[] args) {
        /**
         * 使用stream时，可能会出现空指针的情况：
         *              1.数据源为空
         *              2.中间操作的函数式接口访问了对象的属性或方法
         *              3.方法引用
         *              4.终止操作(forEach、collect等)中的函数对对象进行了操作
         *              5.扁平化
         *              6.并行流
         */
        //这里没有报错null，只是将数组中的null也装进了List
        List<User> collect = Arrays.stream(userArr).collect(Collectors.toList());
        //TODO 注意空指针的几种方式
        /**
         * 方式1：过滤可能存在的null
         */
        collect.stream()
                .filter(Objects::nonNull)//过滤数据源可能存在的null
                .map(User::getName)
                .filter(Objects::nonNull)//校验扁平化后可能存在的null，防止后续的函数式报错
                .forEach(name -> System.out.print(name+" "));
        System.out.println();
        /**
         * 方式2：通过Optional和ifPresent实现
         */
        collect.forEach( user -> {
            //Optional校验非空
            Optional.ofNullable(user)
                    .map(User::getName)
                    .ifPresent(name -> System.out.print(name+" "));//map操作后Optional对象包含非空的才会调用
        });
        /**
         * 方式3：通过三元运算符......
         */

    }
}
