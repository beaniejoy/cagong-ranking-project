package com.cagong.caferanking.interfaces.erroradvice;

import com.cagong.caferanking.application.PasswordWrongException;
import com.cagong.caferanking.application.SessionNotAssignedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class SessionErrorAdvice {

    @ResponseBody
    @ExceptionHandler(PasswordWrongException.class)
    public String handleWrongPassword() {
        return "1";
    }

    @ExceptionHandler(SessionNotAssignedException.class)
    public String handleNotAssigned(HttpServletResponse response) throws IOException {
        return "review/write_handle";
    }
}
