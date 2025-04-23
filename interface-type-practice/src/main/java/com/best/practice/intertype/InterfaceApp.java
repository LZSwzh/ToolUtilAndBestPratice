package com.best.practice.intertype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.best.practice.intertype.graphQL")
@SpringBootApplication
public class InterfaceApp {
    public static void main(String[] args) {
        SpringApplication.run(InterfaceApp.class,args);
    }
}
