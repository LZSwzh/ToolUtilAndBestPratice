package com.best.practice.transaction.aspect;


import com.best.practice.transaction.anno.Idempotent;
import com.best.practice.transaction.util.LockKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * AOP得方式2，借助AspectJ这样得AOP框架
 *
 * @author wangzh
 */
@Aspect
@Slf4j
@Component
public class IdempotentAdvice {

    /** 引入Redisson分布式锁 */
    private RedissonClient redissonClient;


    /** 定义切点，匹配controller包下被Idempotent注解修饰的方法 */
    @Pointcut(
            "execution(* com.best.practice.aop.idempotent.controller..*(..)) && " +
            "@annotation(com.best.practice.aop.idempotent.anno.Idempotent)"
    )
    public void IdempotentPointCut(){}

    /**
     * ProceedingJoinPoint仅支持环绕通知
     * @param joinPoint
     * @return
     */
    @Around("IdempotentPointCut()")
    public Object interceptor(ProceedingJoinPoint joinPoint) {

        //取出切点的方法和注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Idempotent annotation = method.getAnnotation(Idempotent.class);
        //生成自定义key
        if (StringUtils.isEmpty(annotation.keyPrefix())){
            throw new RuntimeException("重复提交前缀不得为空");
        }
        String key = LockKeyUtil.getLockKey(joinPoint);
        //根据分布式锁判断是否重复提交
        RLock lock = redissonClient.getLock(key);
        boolean isLocked = false;
        try {
            isLocked = lock.tryLock();
            if (!isLocked)
                throw new RuntimeException(annotation.message());

            lock.lock(annotation.timeout(), annotation.timeUnit());

            try {
                return joinPoint.proceed();
            } catch (Throwable thr){
                log.info("系统异常，", thr);
                throw new RuntimeException("系统异常，" + thr.getMessage());
            }
        } finally {
            //如果当前线程是锁的持有者，且锁被占有，则释放锁
            if (isLocked && lock.isHeldByCurrentThread())
                lock.unlock();
        }
    }

}
