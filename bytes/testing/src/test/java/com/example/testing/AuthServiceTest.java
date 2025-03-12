package com.example.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private AuthService authService;
    private AccountRepository accountRepositoryMock;

    @BeforeEach
    public void setup() {

//        authService = new AuthServiceImpl(new AccountRepository() {
//            @Override
//            public Account findByUsername(String username) {
//                return new Account(username, "password");
//            }
//        });

        accountRepositoryMock = mock(AccountRepository.class);
        when(accountRepositoryMock.findByUsername("user")).thenReturn(new Account("user", "password"));
        authService = new AuthServiceImpl(accountRepositoryMock);
    }

    @Test
    public void success() {
        assertTrue(authService.authenticate("user", "password"));
        verify(accountRepositoryMock, times(1)).findByUsername("user");
    }

    @Test
    public void failure() {
        assertFalse(authService.authenticate("user", "wrong"));
        verify(accountRepositoryMock, times(1)).findByUsername("user");
    }

}
