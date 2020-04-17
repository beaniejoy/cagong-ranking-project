package com.cagong.caferanking.error;

public class CafeNotFoundException extends RuntimeException {

    public CafeNotFoundException(Long cafeId) {
        super("Could not find Cafe: " + cafeId);
    }
}
