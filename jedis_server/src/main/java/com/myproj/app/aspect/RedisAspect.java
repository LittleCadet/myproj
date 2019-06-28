package com.myproj.app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面类
 * @author LittleCadet
 */
@Slf4j
@Aspect
@Component
public class RedisAspect {

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.myproj.app.tools.DistributeLock.lock(..))")
    public void execute(){}

    @Before("execute()")
    public void deBefore(JoinPoint joinPoint){
        log.info("=============doBefore start==============");
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        log.info("=============doBefore end==============");

    }


    /**
     * 注意：@Around + @After：使用场合是在切点没有返回值的情况下，才会去执行，如果有返回值，这2个注解不能使用，因为没有他们没有返回值
     */
    /*@Around("execution(* com.myproj.app.tools.DistributeLock.*(..))")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint){

        try {
            log.info("=============doAround start==============");
            log.info("methodName :{}" + proceedingJoinPoint.getSignature().getName());
            proceedingJoinPoint.proceed();
            log.info("=============doAround end==============");
        } catch (Throwable throwable) {
            log.error("failed to doAround!");
        }
    }

    @After("execute()")
    public void doAfter(JoinPoint joinPoint){
        log.info("=============doAfter end================");
    }*/

    /**
     * AfterReturning:取得正确的返回值，要满足2个条件：
     * 1.message的类型需要和切点的返回类型对应起来，或者message类型定义为Object,可以接收切点所有类型的返回值
     * 2.用afterReturning时，不能使用@After或者@Around，因为他们2个在切点执行完成之后，再去运行@Around的后置动作，再去运行@After，而他们2个默认都是没有返回值的，即为切点的返回值在他们手里就丢了。
     *
     * 使用场合：在切点有返回值的时候，才会去执行
     *
     * @param joinPoint
     * @param message
     */
    @AfterReturning(value = "execution(* com.myproj.app.tools.DistributeLock.lock(..))",returning = "message")
    public void doAfterReturning(JoinPoint joinPoint,Object message){
        log.info("===============doAfterReturning start===============");

        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        log.info("response:{}",message);
        log.info("===============doAfterReturning end===============");
    }

    /**
     * 只有切点报错时，才会自动执行，否则，不执行
     *
     * 注意：实际没有意义，因为即使在在AfterThrowing中抓住了异常且处理了异常，这个异常依旧会被抛出去，如果在切点之外的方法，没有处理异常，那么依旧会中断程序。
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(pointcut = "execute()",throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable exception){
        log.info("===============doAfterThrowing==============");

        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        if(exception instanceof  Exception){
            log.error("failed to doAfterThrowing,reason : Exception");
        }

    }
}
