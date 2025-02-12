package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TransactionManagerAspect {

    @Around("execution(* com.example.TransferService.*(..))")
    public void manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        // get connection from pool
        // set the connection thread local
        try {
            System.out.println("Begin transaction");
            joinPoint.proceed();
            System.out.println("Commit transaction");
        } catch (Exception e) {
            System.out.println("Rollback transaction");
        } finally {
            // remove connection from thread local
            // return connection to pool
            System.out.println("Close connection");
        }
    }


}
