package com.best.practice.intertype.graphQL.controller;

import com.best.practice.intertype.graphQL.domain.Author;
import com.best.practice.intertype.graphQL.domain.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    /**
     * @QueryMapping       : 代表查询动作
     * @MutationMapping    : 代表修改动作
     * @SubscriptionMapping: 代表订阅动作
     */

    /**
     * @SchemaMapping: 关联schema.graphql文件
     */
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }


    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }
}
