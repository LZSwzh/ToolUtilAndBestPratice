package com.best.pratice.redis.constructmq.bystream;

import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.ErrorHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * ApplicationRunner:在srping生命周期中，当所有spring bean初始化后执行一些操作；可以通过重写run方法定义逻辑
 * DisposableBean：容器销毁Bean前执行一些操作，通过重写destory方法定义逻辑
 */
@Component
public class StreamConsumerRunner implements ApplicationRunner, DisposableBean {

    //stream
    private String consumer = "test_consumer";

    //redis连接工厂
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    //
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    //注入自己的消费者监听器
    @Autowired
    TaskConsumerListener taskConsumerListener;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer;

    @Override
    public void destroy() throws Exception {
        this.streamMessageListenerContainer.stop();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 创建配置对象
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> streamMessageListenerContainerOptions
                = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                .builder()
                .batchSize(10)// 一次性最多拉取多少条消息
                .executor(this.threadPoolTaskExecutor)// 执行消息轮询的执行器
                .errorHandler(new ErrorHandler() {// 消息消费异常的handler
                    @Override
                    public void handleError(Throwable t) {
                        // throw new RuntimeException(t);
                        t.printStackTrace();
                    }
                })
                .pollTimeout(Duration.ZERO)// 超时时间，设置为0，表示不超时（超时后会抛出异常）
                .serializer(new StringRedisSerializer())// 序列化器
                .build();

        // 根据配置对象创建监听容器对象
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> streamMessageListenerContainer = StreamMessageListenerContainer
                .create(this.redisConnectionFactory, streamMessageListenerContainerOptions);

        // 使用监听容器对象开始监听消费（使用的是手动确认方式）
        streamMessageListenerContainer.receive(Consumer.from("group-1", "consumer-1"),
                StreamOffset.create("mystream", ReadOffset.lastConsumed()), this.taskConsumerListener);

        this.streamMessageListenerContainer = streamMessageListenerContainer;
        // 启动监听
        this.streamMessageListenerContainer.start();
    }
}
