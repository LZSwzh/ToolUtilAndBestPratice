package com.best.practice.transaction.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 业务链路追踪注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessTrace {
    String value();

    /**
     * 业务操作类型:查询、新增、修改、删除
     */
    String operateType() default "";

    /**
     * 业务实体类型，用于标记业务实体，用于链路追踪
     */
    Class<?> parameterType() default Object.class;
}
