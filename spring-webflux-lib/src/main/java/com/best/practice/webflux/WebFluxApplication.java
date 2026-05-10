package com.best.practice.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * WebFlux响应式Web框架Lib
 * -  Spring Web MVC 是为 Servlet API 和 Servlet 容器专门构建的。
 * - Spring WebFlux 后来在 5.0 版本中添加。它是完全非阻塞的，支持 Reactive Streams 背压，并运行在 Netty 和 Servlet 容器等服务器上。
 */
@SpringBootApplication
public class WebFluxApplication {
    public static void main( String[] args ) {
        SpringApplication.run(WebFluxApplication.class,args);
    }
}
