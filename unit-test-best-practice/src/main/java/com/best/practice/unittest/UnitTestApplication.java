package com.best.practice.unittest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("com.best.practice.unittest.mapper")
@SpringBootApplication
public class UnitTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UnitTestApplication.class, args);

    }
}
