package com.best.pratice.redis.constructmq.bystream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class StreamTaskProducer {



    @Autowired
    private StringRedisTemplate redisTemplate;
    //TODO 未完成

    /**
     * 初始化一个stream结构，如果不存在可能会报错？org.springframework.data.redis.RedisSystemException: Error in execution; nested exception is io.lettuce.core.RedisCommandExecutionException: NOGROUP No such key ‘your-stream-name...
     */
    @PostConstruct
    public void initializeStream() {
        StreamOperations<String, Object, Object> streamOperations = redisTemplate.opsForStream();
        // 创建一个流
        try {
            streamOperations.createGroup("mystream", ReadOffset.from("0"), "your-consumer-group");
        } catch (Exception e) {
            // 流可能已存在，忽略异常
        }
    }

    /**
     * 生产消息
     */
    public void sendMessage(String streamKey, String messageKey, String message) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(messageKey, message);

        RecordId recordId = redisTemplate.opsForStream().add(streamKey, messageMap);
        if (recordId != null) {
            System.out.println("Message sent to Stream '" + streamKey + "' with RecordId: " + recordId);
        }else {//生产失败.....

        }
    }

}
