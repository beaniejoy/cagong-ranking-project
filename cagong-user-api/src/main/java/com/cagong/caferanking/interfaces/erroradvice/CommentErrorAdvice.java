package com.cagong.caferanking.interfaces.erroradvice;

import com.cagong.caferanking.error.CommentNotExistedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommentErrorAdvice {

    @ExceptionHandler(CommentNotExistedException.class)
    public String handleNotExisted(){
        return "view/comment-no";
    }
}
