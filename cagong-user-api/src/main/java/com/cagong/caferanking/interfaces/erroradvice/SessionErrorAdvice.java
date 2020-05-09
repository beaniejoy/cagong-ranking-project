package com.cagong.caferanking.interfaces.erroradvice;

import com.cagong.caferanking.application.PasswordWrongException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SessionErrorAdvice {

    @ResponseBody
    @ExceptionHandler(PasswordWrongException.class)
    public String handleWrongPassword() {
        return "1";
    }
}
