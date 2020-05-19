package com.cagong.caferanking.error;

public class EmailNotExistedException extends RuntimeException {
    public EmailNotExistedException(String email) {
        super("Could not find User: " + email);
    }
}
