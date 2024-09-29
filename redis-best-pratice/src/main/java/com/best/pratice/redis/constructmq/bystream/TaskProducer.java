package com.best.pratice.redis.constructmq.bystream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TaskProducer {

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 生产消息
     */
    public void sendMessage(String streamKey, String messageKey, String message) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(messageKey, message);

        RecordId recordId = redisTemplate.opsForStream().add(streamKey, messageMap);
        if (recordId != null) {
            System.out.println("Message sent to Stream '" + streamKey + "' with RecordId: " + recordId);
        }
    }

}
