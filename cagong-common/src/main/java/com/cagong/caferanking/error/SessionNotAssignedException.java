package com.cagong.caferanking.error;

public class SessionNotAssignedException extends RuntimeException {
    public SessionNotAssignedException() {
        super("There is no member login information");
    }
}
