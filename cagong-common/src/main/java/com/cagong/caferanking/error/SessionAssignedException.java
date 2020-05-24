package com.cagong.caferanking.error;

public class SessionAssignedException extends RuntimeException {
    public SessionAssignedException() {
        super("There is already member login information");
    }
}
