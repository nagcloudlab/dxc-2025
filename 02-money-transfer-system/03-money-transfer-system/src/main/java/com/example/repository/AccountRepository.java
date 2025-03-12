package com.example.repository;

import com.example.model.Account;

import java.util.Optional;

public interface AccountRepository{
    Optional<Account> loadAccount(String number);
    void updateAccount(Account account);
}
