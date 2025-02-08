package com.best.practice.aop.fieldconvert.reflect;

import com.best.practice.aop.fieldconvert.anno.OAFields;
import io.lettuce.core.dynamic.intercept.MethodInterceptor;
import io.lettuce.core.dynamic.intercept.MethodInvocation;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * AOP得方式1，Spring内置AOP得实现方式
 *
 * MethodBeforeAdvice：前置通知
 * AfterReturningAdvice：后置通知
 * ThrowsAdvice：异常通知
 * MethodInterceptor：环绕通知
 * @author wangzh
 */

public class OAFieldInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object[] arguments = methodInvocation.getArguments();
        for (Object arg:arguments) {
            Class<?> argClazz = arg.getClass();
            OAFields fieldsAnno = argClazz.getAnnotation(OAFields.class);
            if (fieldsAnno != null){

            }
        }
        return null;
    }
}
