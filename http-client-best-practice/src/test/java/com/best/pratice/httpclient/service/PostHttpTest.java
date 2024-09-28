package com.best.pratice.httpclient.service;

import com.best.pratice.httpClient.basicService.PostHttpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostHttpTest {
    @Autowired
    private PostHttpService postHttpService;
    @Test
    public void test1(){
        try {
            postHttpService.postByUrlEncodedType();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
