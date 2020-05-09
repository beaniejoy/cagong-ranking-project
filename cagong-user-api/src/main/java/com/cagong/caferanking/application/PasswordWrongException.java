package com.cagong.caferanking.application;

public class PasswordWrongException extends RuntimeException {
    public PasswordWrongException() {
        super("Password is wrong!");
    }
}
