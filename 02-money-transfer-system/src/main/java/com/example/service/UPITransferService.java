package com.example.service;

import com.example.exception.AccountNotFoundException;
import com.example.exception.InsufficientFundsException;
import com.example.model.Account;
import com.example.model.AccountSnapshot;
import com.example.model.TransactionLog;
import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class UPITransferService implements TransferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UPITransferService.class);

    private final AccountRepository accountRepository; // DI by constructor

    public UPITransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        LOGGER.info("UPITransferService instance created with {}",
                accountRepository.getClass().getSimpleName());
    }



    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        LOGGER.info("Initiating transfer of {} from {} to {}", amount, fromAccountNumber, toAccountNumber);

        // step-1: load from-account
        Account fromAccount = accountRepository.loadAccount(fromAccountNumber)
//                .ifPresent(account -> {
//                    LOGGER.info("Account found: {}", account);
//                })
                .orElseThrow(() -> {
                    LOGGER.error("From-account not found: {}", fromAccountNumber);
                    return new AccountNotFoundException("From-account not found: " + fromAccountNumber);
                });
        // step-2: load to-account
        Account toAccount = accountRepository.loadAccount(toAccountNumber)
                .orElseThrow(() -> {
                    LOGGER.error("To-account not found: {}", toAccountNumber);
                    return new AccountNotFoundException("To-account not found: " + toAccountNumber);
                });

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Not enough balance in " + fromAccountNumber);
        }

        // step-3: debit
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        // step-4: credit
        toAccount.setBalance(toAccount.getBalance() + amount);


        // downcasting
//        if(accountRepository instanceof JdbcAccountRepository jdbcAccountRepository){
//            //JdbcAccountRepository jdbcAccountRepository = (JdbcAccountRepository) accountRepository;
//            jdbcAccountRepository.specialJdbcMethod();
//        }

        // step-5 & 6: update
        accountRepository.updateAccount(fromAccount);
        accountRepository.updateAccount(toAccount);

        // create snapshots for logging or auditing
        var fromSnapshot = new AccountSnapshot(fromAccount.getNumber(), fromAccount.getBalance());
        var toSnapshot = new AccountSnapshot(toAccount.getNumber(), toAccount.getBalance());


        // Transaction Log
        TransactionLog transactionLog = new TransactionLog("123", fromAccountNumber, toAccountNumber, amount, LocalDateTime.now());

        //....


        LOGGER.info("Transfer successful! From {} => {}, To {} => {}",
                fromSnapshot.accountNumber(), fromSnapshot.balance(),
                toSnapshot.accountNumber(), toSnapshot.balance());
    }
}
