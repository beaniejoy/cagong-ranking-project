package com.cagong.caferanking.interfaces.erroradvice;

import com.cagong.caferanking.error.EmailExistedException;
import com.cagong.caferanking.error.EmailNotExistedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserErrorAdvice {

    @ResponseBody
    @ExceptionHandler(EmailExistedException.class)
    public String handleExisted() {
        return "1";
    }

    @ResponseBody
    @ExceptionHandler(EmailNotExistedException.class)
    public String handleNotExisted() {
        return "1";
    }

}
