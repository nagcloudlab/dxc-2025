package com.example.spring_aop_demo;


import org.springframework.stereotype.Service;

@Service
public class TransferService{
    // join point
    public void upiTransfer(){
        System.out.println("UPI Transfer");
    }
    public void neftTransfer(){
        System.out.println("NEFT Transfer");
    }
}
