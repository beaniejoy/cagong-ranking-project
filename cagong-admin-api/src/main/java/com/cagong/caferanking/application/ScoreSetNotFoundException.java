package com.cagong.caferanking.application;

public class ScoreSetNotFoundException extends RuntimeException {
    public ScoreSetNotFoundException(Long cafeId) {
        super("Could not find Scores: " + cafeId);
    }
}
