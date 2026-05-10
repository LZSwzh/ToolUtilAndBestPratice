package com.best.practice.webflux.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * JDK9提供了Reactive-Stream的一系列操作java.util.current.flow
 * Reactive-Stream是JVM面向流的一个库的规范和标准
 * - 处理大批量数据
 * - 有序
 * - 组件间异步通信
 * - 强制非阻塞
 * - 背压模式
 */
@RestController
public class ReactiveStreamController {
    /**
     * Publisher:数据生产者
     * Subscriber:数据消费者
     * Subscription:生产者和消费者之间的契约合同(可请求，可取消)
     */
}
