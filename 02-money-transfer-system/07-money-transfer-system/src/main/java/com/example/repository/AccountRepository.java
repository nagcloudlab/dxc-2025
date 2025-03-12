package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {

}
