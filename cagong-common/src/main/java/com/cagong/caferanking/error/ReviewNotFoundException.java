package com.cagong.caferanking.error;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Long cafeId, Long userId) {
        super("Could not find Review - cafe: " + cafeId + ", user: " + userId);
    }
}
