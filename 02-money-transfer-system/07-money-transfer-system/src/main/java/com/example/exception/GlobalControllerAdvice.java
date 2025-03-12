package com.example.exception;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({
            IllegalArgumentException.class, AccountNotFoundException.class
    })
    public String exceptionHandler(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

}
