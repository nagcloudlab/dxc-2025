package com.example;

import com.example.repository.AccountRepository;
import com.example.repository.AccountRepositoryFactory;
import com.example.service.TransferService;
import com.example.service.UPITransferService;

public class Application {
    public static void main(String[] args) {


        //--------------------------------
        // Init / boot phase
        //--------------------------------

        // DI - Dependency Injection

        // based config, plumbing, wiring, etc.
        AccountRepository jdbcAccountRepository = AccountRepositoryFactory.getAccountRepository("JDBC"); // dependency-1
        AccountRepository jpaAccountRepository = AccountRepositoryFactory.getAccountRepository("JPA"); // dependency-2
        TransferService upiTransferService = new UPITransferService(jdbcAccountRepository); // dependent

        System.out.println();
        //--------------------------------
        // Run/Use phase
        //--------------------------------

        // using dynamic proxies, patching enterprise features around business logic ( AOP )

        upiTransferService.transfer("1", "2", 1000.0);
        System.out.println();
        upiTransferService.transfer("1", "2", 2000.0);

        System.out.println();
        //--------------------------------
        // Shutdown phase
        //--------------------------------

        // clean up, release resources, etc.

    }
}
