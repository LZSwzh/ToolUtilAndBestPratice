package com.best.practice.transaction.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 注解用于标记需要保证幂等的接口，定义了一些默认的配置
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    //默认超时时间3s
    int timeout() default 3;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    //key前缀
    String keyPrefix() default "idempotent";

    //key分割符号
    String delimiter() default ":";

    //提示信息
    String message() default "点击过于频繁，稍后重试";
}
