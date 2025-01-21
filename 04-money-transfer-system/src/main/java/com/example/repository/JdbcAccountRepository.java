package com.example.repository;

import com.example.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;


@Component
public final class JdbcAccountRepository implements AccountRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcAccountRepository.class);

    //private DataSource dataSource; // connection pool
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountRepository(JdbcTemplate jdbcTemplate) {
        LOGGER.info("JdbcAccountRepository instance created");
        //this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Account> loadAccount(String number) {
        LOGGER.info("Loading account from JDBC: {}", number);
        String sql = "SELECT * FROM accounts WHERE number = ?";
        Account account = jdbcTemplate.queryForObject(sql, new Object[]{number}, (rs, rowNum) -> {
            Account acc = new Account();
            acc.setNumber(rs.getString("number"));
            acc.setBalance(rs.getDouble("balance"));
            return acc;
        });
        return Optional.ofNullable(account);
    }

    @Override
    public void updateAccount(Account account) {
        LOGGER.info("Updating account in JDBC: {} with new balance: {}",
                account.getNumber(), account.getBalance());
        String sql = "UPDATE accounts SET balance = ? WHERE number = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getNumber());
    }


    public void specialJdbcMethod() {
        LOGGER.info("Special JDBC method called");
    }

}
