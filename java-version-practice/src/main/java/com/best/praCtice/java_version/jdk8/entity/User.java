package com.best.praCtice.java_version.jdk8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String name;

    private Integer age;

    private Double count;
}
