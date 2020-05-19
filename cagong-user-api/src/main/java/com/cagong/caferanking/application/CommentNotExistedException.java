package com.cagong.caferanking.application;

public class CommentNotExistedException extends RuntimeException {
    public CommentNotExistedException(Long cafeId) {
        super("There are not any comments of Cafe : " + cafeId);
    }
}
