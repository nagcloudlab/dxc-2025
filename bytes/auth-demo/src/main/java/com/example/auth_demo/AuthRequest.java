package com.example.auth_demo;


import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}