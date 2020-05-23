package com.cagong.caferanking.error;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("Could not find User: " + userId);
    }
}
