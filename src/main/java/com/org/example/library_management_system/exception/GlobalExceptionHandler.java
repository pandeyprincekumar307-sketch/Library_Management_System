package com.org.example.library_management_system.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public String handleNotFound(IllegalArgumentException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler({IllegalStateException.class})
    public String handleBadState(IllegalStateException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
