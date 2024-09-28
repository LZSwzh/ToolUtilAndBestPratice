package com.best.pratice.httpclient.service;

import com.best.pratice.httpClient.basicService.GetHttpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GetHttp1Test {
    @Autowired
    private GetHttpService getHttpService;

    @Test
    public void test1(){
        getHttpService.testGetWihtourParam();

    }
    @Test
    public void test2(){
        getHttpService.testGetWithHeader();

    }
    @Test
    public void test3(){
        getHttpService.testGetToGetResHeader();
    }

    @Test
    public void test4(){
        getHttpService.testGetDownImage();
    }

    @Test
    public void test5(){
    }
}
