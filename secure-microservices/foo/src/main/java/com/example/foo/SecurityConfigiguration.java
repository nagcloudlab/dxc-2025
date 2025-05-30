package com.example.foo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfigiguration {

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User
    //             .withUsername("user")
    //             .password("{noop}password")
    //             .roles("USER")
    //             .build();
    //     return new InMemoryUserDetailsManager(user);
    // }

    private final CustomAuthenticationProvider customAuthenticationProvider;
    public SecurityConfigiguration(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for form POST testing (dev only)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signup", "/signup/**").permitAll() // ✅ Allow unauthenticated signup
                        .anyRequest().authenticated())
                .formLogin(withDefaults()) // ✅ Enable default login form
                .logout(withDefaults()) // ✅ Enable default logout
                .securityContext(context -> context.requireExplicitSave(false)); // ✅ Persist login session after manual auth
        return http.build();
    }


}
