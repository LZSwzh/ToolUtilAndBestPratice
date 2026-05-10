package com.best.practice.transaction.util;


import com.best.practice.transaction.anno.Idempotent;
import com.best.practice.transaction.anno.RequestParamKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class LockKeyUtil {

    //根据切入点生成key前缀
    public static String getLockKey(JoinPoint joinPoint){
        //获取方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Idempotent idempotent = method.getAnnotation(Idempotent.class);

        final Object[] args = joinPoint.getArgs();//方法参数
        final Parameter[] parameters = method.getParameters();//方法对象的参数信息
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            //如果方法参数被RequestParamKey修饰,根据分隔符拼接key
            final RequestParamKey keyParam = parameters[i].getAnnotation(RequestParamKey.class);
            if (keyParam != null){

                sb.append(idempotent.delimiter()).append(args[i]);
            }
        }
        //如果没有被该注解修饰的，看类里面是否有这个字段修饰
        if (StringUtils.isEmpty(sb.toString())){
            //获取方法上的多个注解（为什么是两层数组：因为第二层数组是只有一个元素的数组）
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            //循环注解
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object object = args[i];
                //获取注解类中所有的属性字段
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields) {
                    //判断字段上是否有RequestKeyParam注解
                    final RequestParamKey annotation = field.getAnnotation(RequestParamKey.class);
                    //如果没有，跳过
                    if (annotation == null) {
                        continue;
                    }
                    //如果有，设置Accessible为true（为true时可以使用反射访问私有变量，否则不能访问私有变量）
                    field.setAccessible(true);
                    //如果属性是RequestKeyParam注解，则拼接 连接符" & + RequestKeyParam"
                    sb.append(idempotent.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        return idempotent.keyPrefix() + sb;
    }
}
