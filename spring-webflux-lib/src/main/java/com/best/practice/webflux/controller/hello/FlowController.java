package com.best.practice.webflux.controller.hello;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * 13年响应式编程倡议发布；2015年：JAVA社区的Reactive Streams 规范 发布；19年发布JDK9
 * JDK9提供了Reactive-Stream的一系列操作java.util.current.flow
 * Reactive-Stream是JVM面向流的一个库的规范和标准
 * - 处理大批量数据
 * - 有序
 * - 组件间异步通信
 * - 强制非阻塞
 * - 背压模式
 *
 */

@RestController
public class FlowController {
    /**
     * Publisher:数据生产者
     * Subscriber:数据消费者
     * Subscription:生产者和消费者之间的契约合同(可请求，可取消)
     * Processor<T,R> extends Subscriber<T>, Publisher<R>
     * 核心交互流程：
     *   1. Publisher.subscribe(Subscriber) — 订阅
     *   2. Subscriber.onSubscribe(Subscription) — 建立连接
     *   3. Subscription.request(n) — 背压控制，请求 n 个元素
     *   4. Subscriber.onNext(item) — 逐个推送数据
     *   5. Subscriber.onComplete() / Subscriber.onError(Throwable) — 终止信号
     */


    /**
     * Publisher 负责发布数据，它是数据源 的一部分，向订阅者（Subscriber）发送数据
     * public interface Publisher<T> {
     *     void subscribe(Subscriber<? super T> subscriber);
     * }
     */
    public void test_Publisher() throws InterruptedException {
        // 1. 基础用法：SubmissionPublisher + Subscriber
        System.out.println("=== 基础示例 ===");
        try (SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>()) {
            MySubscriber sub = new MySubscriber("订阅者A");
            publisher.subscribe(sub);
            for (int i = 1; i <= 5; i++) {
                publisher.submit(i);
            }
        }
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * Subscriber 是数据的消费者，接收 Publisher 发布的数据流。Subscriber 需要实现四个方法，分别处理不同的状态变化：
     * public interface Subscriber<T> {
     *     void onSubscribe(Subscription s);  // 初始化时调用
     *     void onNext(T t);                  // 当有新数据到达时调用
     *     void onError(Throwable t);         // 当发生错误时调用
     *     void onComplete();                 // 当数据流结束时调用
     * }
     */
    // 自定义 Subscriber
    static class MySubscriber implements Flow.Subscriber<Integer> {
        private Flow.Subscription subscription;
        private final String name;

        MySubscriber(String name) {
            this.name = name;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            System.out.println(name + " 已订阅");
            subscription.request(1); // 背压：先请求1个
        }

        @Override
        public void onNext(Integer item) {
            System.out.println(name + " 收到: " + item);
            // 处理完后再请求下一个
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.err.println(name + " 异常: " + throwable.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println(name + " 完成");
        }
    }
    /**
     * Subscription 是连接 Publisher 和 Subscriber 的纽带，它允许 Subscriber 控制数据流的数量。
     * 背压机制就依赖于 Subscription 进行数据流量控制：
     * public interface Subscription {
     *     void request(long n);   // 请求 n 个数据元素
     *     void cancel();          // 取消数据流
     * }
     */


    /**
     * Processor 是一种特殊的组件，它既是 Subscriber 也是 Publisher，充当中间处理器，允许在接收到数据后对其进行处理再发布给下游。
     * public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
     *     // 既能订阅数据，也能发布处理后的数据
     * }
     */
    // 自定义 Processor（中间转换节点，既是 Subscriber 又是 Publisher）
    static class TransformProcessor extends SubmissionPublisher<String>
            implements Flow.Processor<Integer, String> {

        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(Integer item) {
            // 转换：Integer → String
            submit("处理后的值: " + (item * 2));
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            closeExceptionally(throwable);
        }

        @Override
        public void onComplete() {
            close();
        }
    }


    /**
     * 背压是 Reactive-Streams 规范中的关键概念。它用于处理生产者发送数据过快（正压），
     * 而消费者无法及时处理的情况。没有背压机制的系统很容易出现内存 溢出或性能下降。
     * 通过 Subscription 的 request(n) 方法，消费者可以根据自己的处理能力，向生产者请求合适数量的数据。
     * 如果消费者处理不过来，它可以在没有请求更多数据之前停止接收。
     *
     */
    public static void main(String[] args) throws InterruptedException {


        // 2. 带 Processor 的链式处理
        System.out.println("\n=== Processor 链式示例 ===");
        try (SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>()) {
            TransformProcessor processor = new TransformProcessor();
            // 链路：publisher → processor → endSubscriber
            publisher.subscribe(processor);
            Flow.Subscriber<String> endSubscriber = new Flow.Subscriber<>() {
                private Flow.Subscription subscription;

                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    this.subscription = subscription;
                    subscription.request(1);
                }

                @Override
                public void onNext(String item) {
                    System.out.println("最终收到: " + item);
                    subscription.request(1);
                }

                @Override
                public void onError(Throwable throwable) {}

                @Override
                public void onComplete() {
                    System.out.println("链路完成");
                }
            };
            processor.subscribe(endSubscriber);

            for (int i = 1; i <= 3; i++) {
                publisher.submit(i);
            }
        }
        TimeUnit.SECONDS.sleep(1);
    }
}
