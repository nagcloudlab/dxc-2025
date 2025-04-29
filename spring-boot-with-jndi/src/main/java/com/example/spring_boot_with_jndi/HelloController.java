package com.example.spring_boot_with_jndi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final JdbcTemplate jdbcTemplate;

    public HelloController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/demo")
    public String demo() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS test(id INT PRIMARY KEY, name VARCHAR(50))");
        jdbcTemplate.update("MERGE INTO test KEY(id) VALUES (1, 'JNDI Working')");
        return jdbcTemplate.queryForObject("SELECT name FROM test WHERE id = 1", String.class);
    }
}
