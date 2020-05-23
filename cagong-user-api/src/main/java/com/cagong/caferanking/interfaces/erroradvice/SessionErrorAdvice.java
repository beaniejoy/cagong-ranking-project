package com.cagong.caferanking.interfaces.erroradvice;

import com.cagong.caferanking.error.PasswordWrongException;
import com.cagong.caferanking.error.SessionNotAssignedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@ControllerAdvice
public class SessionErrorAdvice {

    @ResponseBody
    @ExceptionHandler(PasswordWrongException.class)
    public String handleWrongPassword() {
        return "1";
    }

    // TODO 여기 다시한번 봐보자
    @ExceptionHandler(SessionNotAssignedException.class)
    public String handleNotAssigned() throws IOException {
        return "review/write-handle";
    }
}
