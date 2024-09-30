package com.best.pratice.redis.constructmq.bystream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaskConsumerListener implements StreamListener<String, MapRecord<String,String,String>> {

    @Autowired
    private StringRedisTemplate redisTemplate;


    //配置消费者组
    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        //消息ID
        RecordId messageId = message.getId();
        //消息的Key和Value
        Map<String, String> body = message.getValue();

        //打印
        System.out.println("监听器监听id:"+messageId);
        System.out.println("消息内容content:"+body.toString());

        //手动确认ACK
        redisTemplate.opsForStream().acknowledge("mystream",message);
    }
}
