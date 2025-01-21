package com.example.repository;

import com.example.model.Account;

import java.util.Optional;

public sealed interface AccountRepository
permits JdbcAccountRepository,JpaAccountRepository, InMemoryAccountRepository {
    Optional<Account> loadAccount(String number);
    void updateAccount(Account account);
}
