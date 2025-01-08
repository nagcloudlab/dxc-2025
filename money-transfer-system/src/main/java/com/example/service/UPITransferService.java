package com.example.service;


import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.repository.AccountRepositoryFactory;
import com.example.repository.JdbcAccountRepository;

/**
 *  author: team-2
 */


/*

    design issues
    ---------------

    -> dependent tightly coupled with dependency component
        => can't extend or replace with other component
    -> unit testing not possible
        => dev & bug fix will be difficult/slow

    performance issues
    -------------------

    -> for every transfer operation, new instance of JdbcAccountRepository class is created/destroyed
        => slow, memory/resource wastage

    why these issues?
    ------------------

    -> dependent itself creating/managing it's own dependency object

    solution?
    --------

    -> don't create dependency object in dependent class, get it from factory ( design-pattern : factory )

    limitation of factory pattern?

    -> dependent tightly coupled with factory class
    -> on every request, factory class creating new instance of dependency class


    best solution?
    --------------

    -> don't create/lookup, inject by third-party ( Inversion of Control )

    how to implement IOC?

    -> dependency injection ( DI )
        - constructor injection
        - setter injection
    -> aspect oriented programming ( AOP )


    ---------------------------------------------------

    SOLID Principles

    S - Single Responsibility Principle
    O - Open/Closed Principle
    L - Liskov Substitution Principle
    I - Interface Segregation Principle
    D - Dependency Inversion Principle

    ---------------------------------------------------

    Design Patterns

    1. Creational Design Patterns
    2. Structural Design Patterns
    3. Behavioral Design Patterns

    ---------------------------------------------------


 */



public class UPITransferService implements TransferService {

    private AccountRepository accountRepository;

    public UPITransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        System.out.println("AccountRepository instance injected to UPITransferService");
        System.out.println("UPITransferService instance created");
    }


    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {

        // enterprise features
        // -------------------
        // logging
        // transaction management ( ACID )
        // security ( authentication, authorization )
        // validation
        // exception handling
        // performance monitoring
        // caching
        // etc.

        System.out.println("Transferring amount from " + fromAccountNumber + " to " + toAccountNumber);

        //AccountRepository accountRepository = new JdbcAccountRepository(); // Dont create
        //AccountRepository accountRepository = AccountRepositoryFactory.getAccountRepository("JDBC"); // Dont lookup


        // step-1 : Load fromAccount details
        Account fromAccount = accountRepository.loadAccount(fromAccountNumber);
        // step-2 : Load toAccount details
        Account toAccount = accountRepository.loadAccount(fromAccountNumber);

        // step-3 : debit fromAccount
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        // step-4 : credit toAccount
        toAccount.setBalance(toAccount.getBalance() + amount);

        // step-5 : update fromAccount
        accountRepository.updateAccount(fromAccount);
        // step-6 : update toAccount
        accountRepository.updateAccount(toAccount);

        System.out.println("Amount transferred successfully");

    }

}
