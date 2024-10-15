package com.best.practice.aop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/idempotent")
@Api(tags = "测试页面")
public class TestController {
    @GetMapping("/home")
    @ApiOperation("展示首页")
    public String home(){

        return "hello web";
    }
}
