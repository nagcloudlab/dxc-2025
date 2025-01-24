package com.example.service;

import com.example.DxcConfigProperties;
import com.example.exception.AccountNotFoundException;
import com.example.exception.InsufficientFundsException;
import com.example.model.Account;
import com.example.model.AccountSnapshot;
import com.example.model.TransactionLog;
import com.example.repository.AccountRepository;
import com.example.repository.JdbcAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component("transferService")
public class UPITransferService implements TransferService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UPITransferService.class);
    private final AccountRepository accountRepository; // DI by constructor

    @Value("${transfer.limit:100000}")
    private long transferLimit;

//    @Value("${foo.config:foo}")
//    private String fooConfig;
//    @Value("${bar.config:bar}")
//    private String barConfig;


    @Autowired
    private DxcConfigProperties dxcConfigProperties;

    // DI
    @Autowired
    public UPITransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        LOGGER.info("UPITransferService instance created with {}",
                accountRepository.getClass().getSimpleName());
    }


    // AOP
    // every transfer is a transaction , ACID
    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {RuntimeException.class}
    )
    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        LOGGER.info("Initiating transfer of {} from {} to {}", amount, fromAccountNumber, toAccountNumber);

        System.out.println("--------------------");
//        System.out.println("fooConfig = " + fooConfig);
//        System.out.println("barConfig = " + barConfig);
        System.out.println("dxcConfigProperties = " + dxcConfigProperties);
        System.out.println("--------------------");


        if (amount > transferLimit) {
            throw new IllegalArgumentException("Transfer limit exceeded: " + transferLimit);
        }

        // step-1: load from-account
        Account fromAccount = accountRepository.loadAccount(fromAccountNumber)
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

        // step-5 & 6: update
        accountRepository.updateAccount(fromAccount);

        if (1 == 2)
            throw new RuntimeException("Simulating a runtime exception");


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
