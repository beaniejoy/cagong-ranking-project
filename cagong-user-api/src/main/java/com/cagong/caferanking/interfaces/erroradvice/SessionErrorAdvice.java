package com.cagong.caferanking.interfaces.erroradvice;

import com.cagong.caferanking.error.PasswordWrongException;
import com.cagong.caferanking.error.SessionAssignedException;
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
    // 회원에 관한 Session 존재하지 않을시 발생하는 예외처리
    @ExceptionHandler(SessionNotAssignedException.class)
    public String handleNotAssigned() throws IOException {
        return "handle/session-not-exist-handle";
    }
    
    // 회원에 관한 Session 존재시 발생하는 예외처리
    @ExceptionHandler(SessionAssignedException.class)
    public String handleAssigned() throws IOException {
        return "handle/session-exist-handle";
    }
}
