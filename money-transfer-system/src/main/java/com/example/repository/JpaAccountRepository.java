package com.example.repository;

import com.example.model.Account;


/**
 *  author: team-1
 */

public class JpaAccountRepository implements AccountRepository{


    public JpaAccountRepository() {
        System.out.println("JdbcAccountRepository instance created");
    }

    public Account loadAccount(String number) {
        System.out.println("Loading account " + number);
        return new Account(number, 1000.00);
    }

    public void updateAccount(Account account) {
        System.out.println("Updating account " + account.getNumber());
    }


}
