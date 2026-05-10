package com.best.practice.transaction.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解用于配置可选参数生成key,结合之前的分隔符构建一个唯一的key
 * 为什么不直接用参数作为key？接口参数有富文本key过大，影响redis的效率
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParamKey {

}
