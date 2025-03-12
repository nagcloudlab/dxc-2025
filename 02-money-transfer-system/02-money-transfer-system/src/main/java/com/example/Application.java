package com.example;

import com.example.config.MoneyTransferSystemConfiguration;
import com.example.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        LOGGER.info("""
                ===========================================
                =     Welcome to the Money-Transfer App   =
                ===========================================
                """);

        LOGGER.info("=== Application Starting ===");


        //-----------------------------------------------
        // Init / Boot phase
        //-----------------------------------------------


        ConfigurableApplicationContext applicationContext = null;
        //applicationContext = new org.springframework.context.support.ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext = new org.springframework.context.annotation.AnnotationConfigApplicationContext(MoneyTransferSystemConfiguration.class);


        System.out.println();

        //-----------------------------------------------
        // Use phase
        //-----------------------------------------------

        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("ACC001", "ACC002", 100.0);

        System.out.println();


        //-----------------------------------------------
        // Destroy phase
        //-----------------------------------------------

        LOGGER.info("=== Application Finished ===");
    }
}
