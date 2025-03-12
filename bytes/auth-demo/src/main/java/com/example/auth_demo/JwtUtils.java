package com.example.auth_demo;



import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtUtils {

    public String createToken(UserDetails userDetails) {
        // Header + Payload + Signature
        String jwt = Jwts.builder()
                .issuer("me")
                .subject(userDetails.getUsername())
                .audience().add("you").and()
                //.expiration(expiration) //a java.util.Date
                //.notBefore(notBefore) //a java.util.Date
                .issuedAt(new Date()) // for example, now
                .id(UUID.randomUUID().toString()).compact(); //just an example id
        return jwt;
    }
    
    // exract subject from token

    public String extractUsername(String token) {
        // return extractClaim(token, Claims::getSubject);
        return "Nag";
    }





   


}