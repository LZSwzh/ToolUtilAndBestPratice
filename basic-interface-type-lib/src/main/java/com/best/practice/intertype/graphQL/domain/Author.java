package com.best.practice.intertype.graphQL.domain;

import java.util.List;

public record Author (String authorId, String name, String country){
    private static List<Author> authors = List.of(
            new Author("GD-744982","Ming.Z","China"),
            new Author("GD-q23434","tom","America"),
            new Author("GD-98475","King.Z.E","France")
    );
    public static Author getById(String authorId){
        return authors.stream()
                .filter(author -> author.authorId.equals(authorId))
                .findFirst()
                .orElse(null);
    }
}
