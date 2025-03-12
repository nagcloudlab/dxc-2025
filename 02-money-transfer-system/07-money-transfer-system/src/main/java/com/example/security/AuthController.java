package com.example.security;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username) {
        // In a real app, validate the username/password against a database
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.ok("Bearer " + token);
    }
}
