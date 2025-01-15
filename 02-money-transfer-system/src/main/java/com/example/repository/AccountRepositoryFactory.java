package com.example.repository;

public class AccountRepositoryFactory {

    public static AccountRepository getAccountRepository(String tech) {
        return switch (tech) {
            case "JDBC" -> new JdbcAccountRepository();
            case "JPA" -> new JpaAccountRepository();
            default -> throw new IllegalArgumentException("Unknown tech: " + tech);
        };
    }
}
