package com.example.spring_aop_demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect // an implementation cross-cutting concerns
public class AuthroizationAspect{
    // before advice
    // pointcut -> expression that defines where the advice should be applied

    @Before("execution(* *(..))")
    public void checkAuthorization(){
        System.out.println("Checking Authorization");
    }
}

