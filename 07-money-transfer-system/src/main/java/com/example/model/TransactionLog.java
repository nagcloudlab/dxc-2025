package com.example.model;

import java.time.LocalDateTime;

public record TransactionLog(
        String transactionId,
        String fromAccount,
        String toAccount,
        double amount,
        LocalDateTime timestamp
) {}

