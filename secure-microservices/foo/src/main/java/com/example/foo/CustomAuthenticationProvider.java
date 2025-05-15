package com.example.foo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsService userDetailsService;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("CustomAuthenticationProvider: authenticate() called");
         // Custom authentication logic
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // Here you can add your custom authentication logic
        UserDetails user = userDetailsService.loadUserByUsername(username);
        System.out.println("CustomAuthenticationProvider: authenticate() called");
        System.out.println(password);
        System.out.println(user.getPassword());
        // Check if the provided password matches the stored password
        if (!user.getPassword().substring(user.getPassword().lastIndexOf('}')+1).equals(password)) {
             // If authentication fails, throw an exception
            throw new BadCredentialsException("Invalid username or password");
        } else {
            System.out.println("CustomAuthenticationProvider: authenticate() called");
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }

    
    }

    
}
