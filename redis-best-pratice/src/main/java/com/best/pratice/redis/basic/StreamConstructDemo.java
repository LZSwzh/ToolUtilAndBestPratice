package com.best.pratice.redis.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 1.基本介绍：
 * stream是一个按时间排序的消息日志。每条消息都存储在它被插入时的顺序位置，并且有一个唯一的ID标识。
 * 由于其日志特性，Redis Streams允许你访问历史消息，这对于消息的追溯、重放和延迟处理非常有用。
 * 此外相较于SubPub和List支持消息持久化以及消费者组
 * 2.消息结构：
 * 每条消息都可以包含一个或多个字段（field）和值（value）。这类似于一个小的哈希结构，使得每条消息可以携带多个相关的数据点。
 * 3.消息ID:
 * 通常由Redis自动生成保证了全局唯一和顺序的消息ID。你也可以手动指定ID以实现更复杂的场景
 * 4.实时或者历史数据处理：
 * 实时————通过XREAD或XREADGROUP命令，你可以实时监听并处理新添加到流中的消息。
 * 历史————通过XRANGE、XREVRANGE等命令，可以查询流中的历史消息
 * 5.消费者组:
 * 支持多消费者：Redis Streams可以被多个消费者或多个消费者组同时读取，每个消费者组都会跟踪其成员的进度。
 * 消息确认：消费者读取并处理消息后，可以发送确认，表示消息已被处理。未确认的消息可以被再次处理，确保消息不会因消费者失败而丢失。
 * 故障处理：支持挂起的消息列表和消费者超时检测，使得在消费者失败时可以由其他消费者接手处理消息。
 */
@Service
public class StreamConstructDemo {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 消息队列相关命令
     */
    //添加消息
    public void add(String key, Map<String,String> record){
        RecordId recordId = redisTemplate.opsForStream().add(key, record);

        //TODO
    }
}
