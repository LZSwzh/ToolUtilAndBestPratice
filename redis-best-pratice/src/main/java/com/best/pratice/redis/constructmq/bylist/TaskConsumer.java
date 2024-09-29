package com.best.pratice.redis.constructmq.bylist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 这里只是一个基本的实现，另外一些消息幂等等操作没有保证。
 */
@Slf4j
@Service
public class TaskConsumer {

    //用于记录消费出现异常的消息，当处理超时时，备份这些消息，可以用于持久化等操作
    private static final String WARN_QUEUE = "WARN_QUEUE";
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public void consumeTask(String key){
        while (true){
            String value = null;
            try {
                //设置超时时间，不设置超时时间，如果没有消息，会立马返回null，然后下次消费的时候会立即轮询
                value = redisTemplate.opsForList().rightPop(key, 5, TimeUnit.SECONDS);
                //TODO 处理消息
                if (value!=null){
                    System.out.println("消费者消费任务:"+value);
                }
            } catch (Exception e) {//处理消息可能出现异常
                //这里为了防止处理时出现异常，进行持久化操作，消息没处理的时候可进行后续处理
                if(value!=null){
                    redisTemplate.opsForList().leftPush(WARN_QUEUE,value);
                }
                log.error(e.getMessage());
            }
        }
    }
}
