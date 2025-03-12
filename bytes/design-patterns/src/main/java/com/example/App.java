package com.example;

// design issues
// 1. code tangling ( code is mixed with multiple concerns)
// 2. code scattering ( code is spread across multiple concerns)

//solution: 'proxy' design pattern
// can modularize the concerns ( separate the cross-cutting concerns)
// target object becomes clean and focused on business logic


// advantages of proxy design pattern
// 1. separation of concerns


//------------------ Cross-cutting concerns -------------------
// 1. logging
// 2. security
// 3. transaction management
//...
//--------------------------------------------------------------

// Aspect : an implementation of cross-cutting concern(s)
class LogAspect{
    public void logBefore(){
        System.out.println("Before Log: method invoked..");
    }

    public void logAfter(){
        System.out.println("After Log: method executed..");
    }
}
class AuthAspect{
    public void auth(){
        System.out.println("Auth : Role based access");
    }
}
class TransactionManagerAspect{
    public void begin(){
        System.out.println("Begin db Transaction");
    }

    public void commit(){
        System.out.println("Commit db Transaction");
    }

    public void rollback(){
        System.out.println("Rollback db Transaction");
    }
}


// Target Object's class
class TransferService{
    public void upiTransfer(){
        System.out.println("Transfering money");
    }
    public void neftTransfer(){
        System.out.println("Transfering money");
    }
}

// Proxy class's object
class TransferServiceProxy{
    private TransferService target = new TransferService(); // target object ( wrapped object)
    private LogAspect logAspect = new LogAspect();
    private AuthAspect authAspect = new AuthAspect();
    private TransactionManagerAspect transactionManagerAspect = new TransactionManagerAspect();

    public void upiTransfer(){
        logAspect.logBefore();
        authAspect.auth();
        transactionManagerAspect.begin();
        target.upiTransfer();
        transactionManagerAspect.commit();
        logAspect.logAfter();
    }

    public void neftTransfer(){
        logAspect.logBefore();
        authAspect.auth();
        transactionManagerAspect.begin();
        target.neftTransfer();
        transactionManagerAspect.commit();
        logAspect.logAfter();
    }
}


public class App {

    public static void main(String[] args) {
        TransferServiceProxy proxy = new TransferServiceProxy();
        proxy.upiTransfer(); // proxy object
        proxy.neftTransfer();
    }

}

// style of programming : AOP ( Aspect Oriented Programming)

// AOP frameworks
// 1. Spring AOP

//- based on configuration + aspects => generates dynamic proxy using CGlib or JDK proxy


//with that

//-> spring handle/provides many enterprise services

// - transaction management
// - security
// - logging
// - messaging
// - fault tolerance
// - monitoring ingress/egress
// - caching
// - scheduling

