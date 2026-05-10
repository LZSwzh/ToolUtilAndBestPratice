package com.best.practice.version.jdk8.bisic.funcInterface;

import java.util.function.Supplier;

public class SupplierFun {
    public static void main(String[] args) {
        Supplier mySupplier = ()-> Math.random();

        System.out.println(mySupplier.get());
    }
}
