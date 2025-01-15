package com.example.repository;

import com.example.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public final class JdbcAccountRepository implements AccountRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcAccountRepository.class);

    public JdbcAccountRepository() {
        LOGGER.info("JdbcAccountRepository instance created");
    }

    @Override
    public Optional<Account> loadAccount(String number) {
        LOGGER.info("Loading account from JDBC: {}", number);
        // In real scenario, fetch from DB
        if ("1".equals(number) || "2".equals(number)) {
            return Optional.of(new Account(number, 1000.00));
        }
        // If not found, return an empty Optional
        return Optional.empty();
    }

    @Override
    public void updateAccount(Account account) {
        LOGGER.info("Updating account in JDBC: {} with new balance: {}",
                account.getNumber(), account.getBalance());
        // In real scenario, update DB
    }


    public void specialJdbcMethod() {
        LOGGER.info("Special JDBC method called");
    }

}
