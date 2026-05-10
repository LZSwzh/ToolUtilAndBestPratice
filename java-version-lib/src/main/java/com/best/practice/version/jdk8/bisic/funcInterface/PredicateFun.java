package com.best.practice.version.jdk8.bisic.funcInterface;

import java.util.function.Predicate;

public class PredicateFun {
    public static void main(String[] args) {
        /** Predicate<T>：接受一个参数并返回一个布尔值，通常用于过滤操作。
         * - test(T t)：测试给定的参数是否满足条件。
         * - and(Predicate<? super T> other)：返回一个组合的Predicate，只有当当前Predicate和另一个Predicate都返回true时才返回true。
         * - or(Predicate<? super T> other)：返回一个组合的Predicate，只要当前Predicate或另一个Predicate返回true就返回true。
         * - negate()：返回一个反转的Predicate，即当前Predicate的结果取反。
         * - isEqual(Object targetRef)：返回一个Predicate，用于测试给定的参数是否等于目标引用。
         * - not(Predicate<? super T> target)：返回一个反转的Predicate，即当前Predicate的结果取反。
         */
        Predicate<String> ps = s -> s.length() > 3;
        //1.常用test方法使用这个函数式接口
        System.out.println("test   :"+ps.test("hello"));
        //也可以作为方法返回值
        System.out.println("test2  :"+returnPredicateFun().test("hello"));
        //2.常用and方法，用于组合多个条件，返回一个新的Predicate，只有所有条件都为true时才返回true
        Predicate<String> ps2 = s -> s.length() > 3;
        Predicate<String> ps3 = s -> s.length() < 5;
        System.out.println("and    :"+ps2.and(ps3).test("hello"));
        //3.常用or方法，用于组合多个条件，返回一个新的Predicate，只要有一个条件为true时就返回true
        System.out.println("or     :"+ps2.or(ps3).test("hello"));
        //4.常用negate方法，用于反转Predicate的结果，返回一个新的Predicate，当原始Predicate返回false时返回true，当原始Predicate返回true时返回false
        System.out.println("negate :"+ps2.negate().test("hello"));
        //5.常用isEqual方法，用于比较两个对象是否相等，返回一个新的Predicate，当原始Predicate返回true时返回true，当原始Predicate返回false时返回false
        Predicate<String> equalPredicate = Predicate.isEqual("HELLO");
        System.out.println("isEqual:"+equalPredicate.test("HELLO"));
        //6.not方法,11引入的静态方法,类似negate方法
        System.out.println("not    :"+Predicate.not(ps2).test("hello"));
    }

    public static Predicate<String> returnPredicateFun(){
        return s -> s.length() > 3;
    }
}
