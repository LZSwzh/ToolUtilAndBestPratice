package com.best.pratice.redis.constructmq.bylist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskProducer {

    private static final String QUEUE_KEY = "TASK_QUEUE";

    private AtomicInteger count = new AtomicInteger(0);
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 模拟一个每秒 向队列：{TASK_QUEUE} 生产一个任务的生产者
     */
    public void produceTask() throws InterruptedException {
        while (true){
            System.out.println("生产者生产任务:" + count);
            redisTemplate.opsForList().leftPush(QUEUE_KEY,count.toString());
            count.getAndAdd(1);
            Thread.sleep(1000);
        }
    }
}
