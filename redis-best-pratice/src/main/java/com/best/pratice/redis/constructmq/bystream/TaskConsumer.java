package com.best.pratice.redis.constructmq.bystream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class TaskConsumer implements StreamListener<String, ObjectRecord<String,String>> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //配置消费者组
    @Override
    public void onMessage(ObjectRecord<String, String> message) {
        String stream = message.getStream();
        String messageId = message.getId().toString();
        String messageBody = message.getValue();

        System.out.println("Received message from Stream '" + stream + "' with messageId: " + messageId);
        System.out.println("Message body: " + messageBody);
    }
}
