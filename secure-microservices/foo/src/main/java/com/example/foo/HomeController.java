package com.example.foo;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal,HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        String roles = authentication.getAuthorities().toString();
        System.out.println("Username: " + username);
        System.out.println("Roles: " + roles);

        //---

        // You can also use the Principal object directly
        if (principal != null) {
            String principalName = principal.getName();
            System.out.println("Principal Name: " + principalName);
        } else {
            System.out.println("Principal is null");
        }
        
        //---

        if(request.isUserInRole("ROLE_ADMIN")) {
            System.out.println("User is in ROLE_ADMIN");
        } else {
            System.out.println("User is NOT in ROLE_ADMIN");
        }

        //---

        return "home"; // This will resolve to src/main/resources/templates/home.html
    }
    
}
