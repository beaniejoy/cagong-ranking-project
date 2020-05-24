package com.cagong.caferanking.error;

public class CommentNotExistedException extends RuntimeException {
    public CommentNotExistedException(Long cafeId) {
        super("There are not any comments of Cafe : " + cafeId);
    }
}
