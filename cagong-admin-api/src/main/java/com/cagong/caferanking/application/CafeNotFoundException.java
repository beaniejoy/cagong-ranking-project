package com.cagong.caferanking.application;

public class CafeNotFoundException extends RuntimeException {

    public CafeNotFoundException(Long cafeId) {
        super("Could not find Cafe no. " + cafeId);
    }
}
