package com.example.foo;



import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FakeUserRepository {

    private final Map<String, UserEntity> users = new ConcurrentHashMap<>();

    public FakeUserRepository() {
        // Pre-populate with a user
        users.put("admin", new UserEntity("admin", "{noop}admin", "ROLE_ADMIN"));
        users.put("user", new UserEntity("user", "{noop}user", "ROLE_USER"));
    }

    public UserEntity findByUsername(String username) {
        return users.get(username);
    }

    public void save(UserEntity user) {
        users.put(user.getUsername(), user);
    }

    public boolean exists(String username) {
        return users.containsKey(username);
    }
}
