package com.example.testing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password) {
        if (authService.authenticate(username, password)) {
            return "Welcome";
        } else {
            return "Invalid credentials";
        }
    }

}
