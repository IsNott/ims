package com.nott.common.aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * @author Nott
 * @Date 2022/10/9
 */

@Aspect
@Component
@Slf4j
public class MyAopAspect {
    public static final Logger logger = LoggerFactory.getLogger(MyAopAspect.class);

    //@Pointcut(value = "within(com.nott.ims.minio..*)")
    //private void myPointcut() {
    //
    //}

    @Around("execution(* com.nott.ims.*.*..*(..))")
    @SneakyThrows
    public Object aopAround(ProceedingJoinPoint joinPoint) {
        String methodsModifier = Modifier.toString(joinPoint.getSignature().getModifiers());
        String name = joinPoint.getSignature().getName();
        //if ("public".equalsIgnoreCase(methodsModifier)) {
        Long startTime = System.currentTimeMillis();
        Object returnVal = joinPoint.proceed();
        long passTime = System.currentTimeMillis() - startTime;
        logger.info(name + "Start... pass" + passTime + "ms");
        //} else {
        //    returnVal = joinPoint.proceed();
        //}
        return returnVal;
    }
}
