package com.example;


import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TransferService{

    @PostConstruct
    public void init(){
        System.out.println("Init method called");
    }


    public void upiTransfer(){
        System.out.println("UPI Transfer");
    }
    public void neftTransfer(){
        System.out.println("NEFT Transfer");
        throw new RuntimeException("NEFT Transfer failed");
    }


}
