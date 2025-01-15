package com.example.repository;

import com.example.model.Account;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryAccountRepository implements AccountRepository{

    private static final Map<String, Account> accountMap = new HashMap<>();

    static {
        Account account1 = new Account("1", 1000);
        Account account2 = new Account("2", 2000);
        Account account3 = new Account("3", 3000);

        accountMap.put(account1.getNumber(), account1);
        accountMap.put(account2.getNumber(), account2);
        accountMap.put(account3.getNumber(), account3);
    }

    @Override
    public Account loadAccount(String number) {
        return accountMap.get(number);
    }

    @Override
    public void updateAccount(Account account) {
        accountMap.put(account.getNumber(), account);
    }


}
