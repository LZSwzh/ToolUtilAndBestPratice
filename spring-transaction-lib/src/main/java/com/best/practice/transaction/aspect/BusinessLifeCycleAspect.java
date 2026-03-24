package com.best.practice.transaction.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 业务生命周期切面
 * note:切面的执行顺序:Around->Before->After->AfterReturning->AfterThrowing
 */
@Aspect
@Component
@Slf4j
public class BusinessLifeCycleAspect {



    @Pointcut("@annotation(com.best.practice.transaction.anno.BusinessTrace)")
    public void businessTraceCut(){}


    @Around("businessTraceCut()")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("trace start");
        Object result = joinPoint.proceed();
        log.info("trace end");
        return result;
    }

}
