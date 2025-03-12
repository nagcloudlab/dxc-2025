package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect // an implementation cross-cutting concerns
public class SampleAspect {


//    // before advice
//    // pointcut -> expression that defines where the advice should be applied
//    @Before("execution(* com.example.TransferService.*(..))")
//    public void beforeAdvice() {
//        System.out.println("Before advice");
//    }
//
//    // after returning advice
//    @AfterReturning("execution(* com.example.TransferService.*(..))")
//    public void afterReturningAdvice() {
//        System.out.println("After returning advice");
//    }
//
//    // after throwing advice
//    @AfterThrowing(pointcut = "execution(* com.example.TransferService.*(..))", throwing = "e")
//    public void afterThrowingAdvice(Throwable e) {
//        System.out.println("After throwing advice - " + e.getMessage());
//    }
//
//    // after advice
//    @After("execution(* com.example.TransferService.*(..))")
//    public void afterAdvice() {
//        System.out.println("After advice");
//    }


    @Around("execution(* com.example.TransferService.*(..))")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before advice");
        try {
            joinPoint.proceed();
            System.out.println("After returning advice");
        } catch (Exception e) {
            System.out.println("After throwing advice - " + e.getMessage());
        } finally {
            System.out.println("After advice");
        }
    }

}

