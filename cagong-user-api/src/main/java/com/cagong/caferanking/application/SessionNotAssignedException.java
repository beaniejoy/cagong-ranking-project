package com.cagong.caferanking.application;

public class SessionNotAssignedException extends RuntimeException {
    public SessionNotAssignedException() {
        super("There is no member login information");
    }
}
