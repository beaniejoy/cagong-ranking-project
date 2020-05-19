package com.cagong.caferanking.error;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException() {
        super("Password is wrong!");
    }
}
