package com.myproj.app.aspect;

import com.myproj.app.tools.DistributeLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 切面2：分布式锁完全依赖aop的@Around实现
 * @author LittleCadet
 */
@Slf4j
@Aspect
@Component
public class RedisAspectV2 {

    @Autowired
    private DistributeLock distributeLock;

    @Pointcut("execution(* com.myproj.app.service.RedisServiceImpl.processData(..))")
    public void executeV2(){}

    @Around("executeV2()")
    public Boolean doAround(ProceedingJoinPoint proceedingJoinPoint){
        log.info("=============doAround start============");

        Object[] args = proceedingJoinPoint.getArgs();

        Boolean flag = distributeLock.lock((String)args[0],(String)args[1]);

        if(flag) {
            try {
                proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                log.error("failed to execute doAround");
            }
        }

        log.info("flag:{}",flag);

        log.info("=============doAround end============");

        return flag;

    }



}
