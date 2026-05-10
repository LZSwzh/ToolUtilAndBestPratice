package com.best.practice.webflux.controller;

import com.best.practice.webflux.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * 介绍Reactor常用的两种类型Flux和Mono
 */
@RestController
class ReactiveTypeController {
    @GetMapping("reactive/flux")
    public Flux<List<User>> helloFlux() {
        return Flux.just(List.of(new User("lisi",23),new User("tom",33)));
    }

    @GetMapping("reactive/mono")
    public Mono<User> helloMono(){
        return Mono.justOrEmpty(new User("zhangsan",23));
    }
}