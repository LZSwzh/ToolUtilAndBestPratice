package com.best.practice.version.jdk8.entity;


public class User {
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

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    private String name;

    private Integer age;

    private Double count;

    public User() {

    }

    public User(String name, Integer age, Double count) {
        this.name = name;
        this.age = age;
        this.count = count;
    }
}
