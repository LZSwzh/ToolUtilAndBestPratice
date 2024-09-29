package com.best.pratice.redis.mq.listmq;

import com.best.pratice.redis.constructmq.bylist.TaskConsumer;
import com.best.pratice.redis.constructmq.bylist.TaskProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ListMQTest {

    @Autowired
    private TaskConsumer taskConsumer;

    @Autowired
    private TaskProducer taskProducer;

    @Test
    public void test() throws InterruptedException {
        //开启生产者线程，模拟每秒生产一个消息
        Thread producerThread = new Thread(() -> {
            try {
                taskProducer.produceTask();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producerThread.start();
        //休眠一秒，保证生产者先执行
        Thread.sleep(1000);

        Thread consumeThread = new Thread(() -> {
            taskConsumer.consumeTask("TASK_QUEUE");
        });
        consumeThread.start();

        // 等待一段时间让生产者和消费者运行
        Thread.sleep(8000); // 可以根据需要调整时间

        //这里是为了主线程不要关闭
        consumeThread.join();
        producerThread.join();
    }
}
