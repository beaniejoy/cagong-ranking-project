package com.cagong.caferanking.application;

public class EmailNotExistedException extends RuntimeException {
    public EmailNotExistedException(String email) {
        super("Could not find User: " + email);
    }
}
