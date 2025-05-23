package com.bestpratice.transation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class TransationApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TransationApp.class, args);
        for(String beanName:context.getBeanDefinitionNames()){
            log.info(">>>>>>>>>>>>>>>>>>>>成功装配:{}<<<<<<<<<<<<<<<<<<<<",beanName);
        }
    }
}
