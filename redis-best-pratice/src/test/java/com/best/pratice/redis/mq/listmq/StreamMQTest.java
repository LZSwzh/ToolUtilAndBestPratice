package com.best.pratice.redis.mq.listmq;

import com.best.pratice.redis.constructmq.bystream.StreamTaskConsumerListener;
import com.best.pratice.redis.constructmq.bystream.StreamTaskProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class StreamMQTest {
    @Autowired
    private StreamTaskProducer streamTaskProducer;

    @Autowired
    private StreamTaskConsumerListener consumerListener;

    @Test
    public void testStreamMQ() throws InterruptedException {
        String streamKey = "mystream";
        // 使用生产者发送消息
        streamTaskProducer.sendMessage(streamKey,"a","111");
        streamTaskProducer.sendMessage(streamKey,"b","112");
        Thread.sleep(10000000000l);
    }
}
