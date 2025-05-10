package com.best.practice.java_version.jdk8.bisic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 介绍lambda的flatMap方法
 * 1. flatMap方法的作用
 *     flatMap方法接收一个函数，这个函数的参数是当前流中的元素，返回值是"流"【强调返回值是stream】
 */
public class flatMapTest {
    public static void main(String[] args) {
        //数据准备
        Book a = new Book("A", 43.0);
        Book b = new Book("B", 23.0);
        Author author1 = new Author();
        author1.name = "author1";
        author1.books.add(a);
        author1.books.add(b);
        Book c = new Book("C", 83.0);
        Book d = new Book("D", 33.0);
        Author author2 = new Author();
        author2.name = "author2";
        author2.books.add(c);
        author2.books.add(d);

        List<Author> authList = List.of(author1, author2);

        Book maxAccountBook = authList.stream().flatMap(au -> au.books.stream())
                .max(new Comparator<Book>() {
                    @Override
                    public int compare(Book t0, Book t1) {
                        return t0.account.compareTo(t1.account);
                    }
                }).get();
        System.out.println(maxAccountBook.bookName+":"+maxAccountBook.account);
        // 使用Comparator优化比较器,同时使用orElseThrow 抛出异常
        Book maxAccountBook2 = authList.stream()
                .flatMap(au -> au.books.stream())
                .max(Comparator.comparingDouble(t-> t.account))
                .orElseThrow(); // 或者使用 .get()，但建议用 orElseThrow 更安全
        System.out.println(maxAccountBook2.bookName+":"+maxAccountBook2.account);
    }


    static class Author{
        public String name = "";

        public List<Book> books = new LinkedList<>();

    }

    static class Book{
        public String bookName = "";
        public Double account = 0.0;

        public Book(String bookName, Double account) {
            this.bookName = bookName;
            this.account = account;
        }
    }
}
