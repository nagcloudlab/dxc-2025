package com.example;

import com.example.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = "com.example")
@EnableAutoConfiguration // apply many conditions to enable auto-configuration
@EnableTransactionManagement
//@PropertySource("classpath:foobar.properties")
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
        applicationContext= SpringApplication.run(Application.class);


        System.out.println();

        //-----------------------------------------------
        // Use phase
        //-----------------------------------------------

        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("ACC001", "ACC002", 100.00);

        System.out.println();


        //-----------------------------------------------
        // Destroy phase
        //-----------------------------------------------

        LOGGER.info("=== Application Finished ===");
    }
}
