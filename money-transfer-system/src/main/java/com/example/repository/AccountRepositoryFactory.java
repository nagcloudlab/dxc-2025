package com.example.repository;

public class AccountRepositoryFactory {

    public static AccountRepository getAccountRepository(String tech) {
        if (tech.equals("JDBC")) {
            return new JdbcAccountRepository();
        } else if (tech.equals("JPA")) {
            return new JpaAccountRepository();
        } else {
            throw new IllegalArgumentException("Unknown tech " + tech);
        }
    }

}
