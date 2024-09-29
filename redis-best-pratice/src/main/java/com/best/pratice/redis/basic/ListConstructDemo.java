package com.best.pratice.redis.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ListConstructDemo {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    public Long getSize(String key){
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 插入元素
     */
    //从左边插入
    public void leftPush(String key, String value){
        redisTemplate.opsForList().leftPush(key,value);
    }

    //  从右边push
    public void rightPush(String key, String value){
        redisTemplate.opsForList().rightPush(key,value);
    }

    /***
     * 取元素
     */
    //从左边pop
    public void leftPop(String key){
        redisTemplate.opsForList().leftPop(key);
    }

    //从右边pop
    public void rightPop(String key){
        redisTemplate.opsForList().rightPop(key);
    }
    //阻塞式LPOP,当列表为空时不会立即返回结果，等待有新元素添加后返回
    public void blockLPop(String key,Long timeout){
        String value = redisTemplate.opsForList().leftPop(key, timeout, TimeUnit.MILLISECONDS);
        System.out.println("value = " + value);
    }
    //阻塞式RPOP
    public void blockRPop(String key,Long timeout){
        String value = redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.MILLISECONDS);
        if (value!=null) {
            System.out.println("value = " + value);
        } else{
            System.out.println("已超时，未获取到元素");
        }
    }

    /**
     * 根据key和索引查看某个成员
     */
    public void index(String key,Long index){
        String member = redisTemplate.opsForList().index(key, index);
        System.out.println(member);
    }

    /**
     * 范围查询
     * @param key
     */
    public void rangeQuery(String key,Long start,Long end){
        List<String> memberList = redisTemplate.opsForList().range(key, start, end);
        System.out.println(memberList.toString());
    }

    /**
     * lpushx:当键存在的时候放入队列左边
     */
    public void leftPushX(String key,String value){
        redisTemplate.opsForList().leftPushIfPresent(key,value);
    }

    /**
     * rpushx:当键存在的时候放到队列右边
     */
    public void rightPushX(String key,String value){
        redisTemplate.opsForList().rightPushIfPresent(key,value);
    }

    /**
     * 删除给定值的元素
     * @param key
     */
    public void delMemberByValue(String key,Long count,String value){
        //传入key、计数(删除的数量)、value
        Long delCount = redisTemplate.opsForList().remove(key, count, value);
        System.out.println("已删除"+delCount+"个匹配的元素");
    }




    public void printList(String key){
        Long size = redisTemplate.opsForList().size(key);

        for (int i = 0; i < size; i++) {
            String member = redisTemplate.opsForList().index(key, i);
            System.out.println(member);
        }
    }
}
