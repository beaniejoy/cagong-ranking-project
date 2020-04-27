package com.cagong.caferanking.error;

public class ScoreSetNotFoundException extends RuntimeException {
    public ScoreSetNotFoundException(Long cafeId) {
        super("Could not find Scores: " + cafeId);
    }
}
