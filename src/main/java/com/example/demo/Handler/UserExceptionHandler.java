package com.example.demo.Handler;

import com.example.demo.Exception.UserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"Repository", "Controller"})
public class UserExceptionHandler {
    @ExceptionHandler(UserException.class)
    public String custom() {
        return "Hello MyException!";
    }
}
