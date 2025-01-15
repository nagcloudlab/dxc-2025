package com.example;

import com.example.repository.AccountRepository;
import com.example.repository.AccountRepositoryFactory;
import com.example.service.TransferService;
import com.example.service.UPITransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        LOGGER.info("""
        ===========================================
        =     Welcome to the Money-Transfer App   =
        ===========================================
        """);

        LOGGER.info("=== Application Starting ===");

        // 1) Decide which repository to use
        var chosenTech = "JDBC"; // or "JPA"
        var accountRepository = AccountRepositoryFactory.getAccountRepository(chosenTech);
        // 2) Manually inject into UPITransferService
        var transferService = new UPITransferService(accountRepository);

        System.out.println();
        // 3) Use the transfer service
        transferService.transfer("1", "2", 1000.0);
        System.out.println();
        transferService.transfer("1", "2", 2000.0);

        System.out.println();

        LOGGER.info("=== Application Finished ===");
    }
}
