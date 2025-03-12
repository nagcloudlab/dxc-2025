package com.example.testing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

// author : team-2

@Repository
public class JpaAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Account findByUsername(String username) {
        return entityManager.find(Account.class, username);
    }

    @Transactional
    public void save(Account account) {
        entityManager.persist(account);
    }

    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from Account").executeUpdate();
    }

}
