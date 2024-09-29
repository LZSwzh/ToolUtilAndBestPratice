package com.best.pratice.redis;

import com.best.pratice.redis.basic.ListConstructDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BasicTest {
    @Autowired
    private ListConstructDemo listConstructDemo;
    @Test
    public void ListTest(){
        listConstructDemo.leftPush("a","111");

        listConstructDemo.leftPush("a","222");

        listConstructDemo.leftPush("a","333");

        listConstructDemo.leftPush("a","444");
        System.out.println("list的大小"+listConstructDemo.getSize("a"));
        listConstructDemo.printList("a");


    }
}
