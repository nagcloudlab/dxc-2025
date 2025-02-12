package com.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect // an implementation cross-cutting concerns
public class SampleAspect{
    // before advice
    // pointcut -> expression that defines where the advice should be applied
    @Before("execution(void com.example.TransferService.*(..))")
    public void checkAuthorization(){

    }
}

