package com.best.practice.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableTransactionManagement
public class TransactionApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TransactionApp.class, args);
        for(String beanName:context.getBeanDefinitionNames()){
            log.info(">>>>>>>>>>>>>>>>>>>>成功装配:{}<<<<<<<<<<<<<<<<<<<<",beanName);
        }
        log.info(">>>>>>>>>>>>>>>>>>>>【TransactionApp启动成功】<<<<<<<<<<<<<<<<<<<<");
    }
}
