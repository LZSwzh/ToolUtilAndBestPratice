package com.best.pratice.httpClient.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AlphaController {
    @PostMapping("/alpha/testIOException")
    public String testIOException(@RequestBody String requestBody) throws IOException {
        System.out.println("请求体: " + requestBody);
        throw new IOException("模拟IOException");
    }
}
