package com.best.practice.webflux.controller.hello;

import com.best.practice.webflux.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * Reactor 是 Spring 的响应式编程库，完全基于 Reactive-Streams 规范。
 * 通过 Flux 和 Mono 两种 Publisher 来实现数据流的发布
 * - Mono:表示一个包含 0 或 1 个数据的异步流。
 * - Flux:表示一个包含 0 到多个数据的异步流。
 */
@RestController
class ReactorController {

    @GetMapping("reactive/flux")
    public Flux<List<User>> helloFlux() {
        return Flux.just(List.of(new User("lisi",23),new User("tom",33)));
    }

    @GetMapping("reactive/mono")
    public Mono<User> helloMono(){
        return Mono.justOrEmpty(new User("zhangsan",23));
    }
}