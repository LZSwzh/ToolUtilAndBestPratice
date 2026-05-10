package com.best.practice.version.jdk8.optional;

import java.util.Optional;

/**
 * Optional 是 Java 8 引进的一个新特性，通常用于缓解常见的空指针异常问题;
 * Optional is intended to provide a limited mechanism for library method return types where there needed to be a clear
 * way to represent “no result," and using null for such was overwhelmingly likely to cause errors.
 *
 * 翻译:是一种旨在为库方法的返回类型提供有限机制的工具，用于那些需要明确表示 “无结果” 的情况，如果在这些场景中使用 null，则非常可能会导致错误
 * - 重点1:作为返回值
 * - 重点2:表达返回值没有结果的可能性
 * - 重点3:如果在这些场景使用null可能导致错误
 */
public class OptionalTest {
    public static void main(String[] args) {
        /** ============================  1.Optional对象构建  ================================ */
        /* 1.1.Of方法使用非空值构建一个Optional */
        Optional<String> res0 = Optional.of("张三");
        //报错:of方法不能传入null,否则报错空指针异常
//        Optional<Object> res1 = Optional.of(null);

        /* 1.2.OfNullable方法可使用null或者非空值构建Optional,当是null返回Optional.Empty() */
        Optional<Object> o2 = Optional.ofNullable(null);

        /* 1.3.构建一个值为空的Optional对象 */
        Optional.empty();

        /** ============================  2.orElse API    ================================ */
        /* 2.1.ifPresent(Consumer consumer),当Optional存在值,则执行传入的消费者函数式接口*/
        Optional.ofNullable(null).ifPresent((v)-> System.out.println(v));//null不会调用后面的消费者

        /* 2.2.OrElse(T value)如果Optional中有值则返回,没有则返回OrElse中传入的参数*/
        Object defaultVal = Optional.ofNullable(null).orElse("默认值");
        System.out.println("defaultValue:"+defaultVal);

        /* 2.3.OrElseGet(Supplier supplier)如果Optional中有值则返回,没有则返回OrElseGet生产者函数式接口的返回*/
        Object defaultVal2 = Optional.ofNullable(null).orElseGet(()->"默认值2");
        System.out.println("defaultValue2:"+defaultVal2);

        /* 2.4.OrElseThrow(),传入异常的生产者或者默认啥也不传。当Optional不存在值的时候返回这个异常 */
        Optional.ofNullable(null).orElseThrow(()->new RuntimeException("null异常提醒"));
    }
}
