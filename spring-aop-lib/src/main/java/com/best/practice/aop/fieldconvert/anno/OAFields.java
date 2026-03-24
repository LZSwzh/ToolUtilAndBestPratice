package com.best.practice.aop.fieldconvert.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Nova007466
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OAFields {
    /** 每个元素存储 字段名和键值；  默认根据,分割 */
    String[] values() default {};

    String[] child() default {};

    String separator() default ",";
}
