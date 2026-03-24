package com.best.practice.intertype.graphQL.domain;

import java.util.Arrays;
import java.util.List;

/**
 * 一、基本介绍
 * Java record ： 14预览,16正式,17支持与密封类结合,21引入记录模式
 * 不可变的数据结构。自动生成全参构造和get/equals/hashcode/toString,注意是不可变的，无法set修改
 * 隐式的声明为final，无法包含抽象方法和子类
 *
 * 二、适用场景
 * DTO传输数据、函数式编程传递一组数据
 * 配置类、配合模式匹配结构(21)、事件驱动架构中定义事件对象
 *
 * 三、字段关系
 * id书籍的主键、name书籍名称、pageCount页数、authorId和Author的映射关系
 * 这里不直接写Author类型，因为这是DDD推荐的最佳实践
 */
public record Book(String id,String name,int pageCount,String authorId) {
    //模拟一组数据
    private static List<Book> books = Arrays.asList(
            new Book("BK-868263","Effective Java",300,"GD-744982"),
            new Book("bk-239578","Clean Code",300,"GD-q23434"),
            new Book("BK-987497","Domain Driven Design",300,"GD-98475")
    );
    //提供一个查询方法
    public static Book getById(String id){
        return books.stream()
                .filter(book -> book.id.equals(id))
                .findFirst()
                .orElse(null);
    }
}
