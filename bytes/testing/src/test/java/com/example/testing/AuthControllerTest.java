package com.example.testing;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AuthControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testLogin() {
        String response = restTemplate.postForObject("/auth?username=test&password=test", null, String.class);
        assertEquals("Welcome", response);
    }


    //----------------------


    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    AccountRepository accountRepository;

    Account account = new Account("test", "test");

    @BeforeEach
    public void setUp() {
        accountRepository.deleteAll();
        accountRepository.save(account);
    }

    @Test
    public void test() {
        Account account = accountRepository.findByUsername("test");
        assertEquals("test", account.getUsername());
    }

}
