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
        listConstructDemo.leftInsert("a","111");

        listConstructDemo.leftInsert("a","222");

        listConstructDemo.leftInsert("a","333");

        listConstructDemo.leftInsert("a","444");
        System.out.println(listConstructDemo.getSize("a"));


    }
}
