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
public class StreamTaskConsumerListener implements StreamListener<String, ObjectRecord<String,String>> {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //配置消费者组名称
    private String group = "group-1";


    @Override
    public void onMessage(ObjectRecord<String, String> message) {
        //消息ID
        RecordId messageId = message.getId();
        //消息的Key和Value
        String messageBody = message.getValue();

        //打印
        System.out.println("监听器监听id:"+messageId);
        System.out.println("消息内容content:"+messageBody.toString());

        //手动确认ACK
        try {
            //TODO...开始消费
            redisTemplate.opsForStream().acknowledge(group,message);
        } catch (Exception e) {
            //当消费出现异常，可以做一些操作防止消息丢失(如再次入队、或者记录错误)
            //...
        }
    }
}
