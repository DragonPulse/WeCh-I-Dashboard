package com.demo.wechatint.wechatintegration.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LogTimeAspect {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering into "+joinPoint.getSignature());
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("Exiting from "+joinPoint.getSignature());
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        //System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }
}