package com.best.practice.unittest.service.impl;

import com.best.practice.unittest.domain.dto.UserDTO;
import com.best.practice.unittest.domain.entities.UserEntity;
import com.best.practice.unittest.mapper.DemoMapper;
import com.best.practice.unittest.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

/**
 * 记录JUnit的一些常用注解
 */
@SpringBootTest
public class DemoServiceImplTest{
    /**
     * @Description: 模拟测试环境初始化
     * @BeforeEach 每个测试方法执行前都会执行一次,用于初始化测试环境
     */
    @BeforeEach
    public void setUp() throws InterruptedException {
        System.out.println("================================BeforeEach 初始化资源开始================================");
        for (int i = 0; i < 3; i++) {
            System.out.println("BeforeEach 初始化资源"+i+"......");
            Thread.sleep(1000);
        }
        System.out.println("================================BeforeEach 初始化资源 结束================================");
    }

    /**
     * @Description: 模拟测试环境销毁
     * @BeforeEach 每个测试方法执行后都会执行一次,用于清理测试环境
     */
    @AfterEach
    public void tearDown() throws InterruptedException {
        System.out.println("================================AfterEach 销毁资源开始================================");
        for (int i = 0; i < 3; i++) {
            System.out.println("AfterEach 销毁资源"+i+"......");
            Thread.sleep(1000);
        }
        System.out.println("================================AfterEach 销毁资源结束================================");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll 初始化资源开始......");
        System.out.println("BeforeAll 初始化资源结束......");
    }

    @Test
    public void testDoTest(){
        System.out.println("执行DoTest方法......");
    }


}
