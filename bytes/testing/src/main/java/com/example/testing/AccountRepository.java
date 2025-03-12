package com.example.testing;


import org.springframework.stereotype.Repository;



public interface AccountRepository {
    public void save(Account account) ;
    public void deleteAll() ;
    public Account findByUsername(String username) ;
}
