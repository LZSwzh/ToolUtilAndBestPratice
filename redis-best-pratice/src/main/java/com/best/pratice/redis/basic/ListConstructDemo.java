package com.best.pratice.redis.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ListConstructDemo {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public Long getSize(String key){
        return redisTemplate.opsForList().size(key);
    }

    public void leftInsert(String key,String value){
        redisTemplate.opsForList().leftPush(key,value);
    }

    public void rightInsert(String key,String value){
        redisTemplate.opsForList().rightPush(key,value);
    }

    public void leftOut(String key,String value){
        redisTemplate.opsForList().leftPop(key);
    }
    public void rightOut(String key,String value){
        redisTemplate.opsForList().rightPop(key);
    }
}
