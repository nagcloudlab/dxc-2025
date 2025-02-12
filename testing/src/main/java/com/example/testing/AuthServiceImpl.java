package com.example.testing;


import org.springframework.stereotype.Service;

// author : team-1

@Service
public class AuthServiceImpl {

    private final AccountRepository accountRepository;

    public AuthServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean authenticate(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return account.getPassword().equals(password);
        }
        return false;
    }

}
