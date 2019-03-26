package com.example.movieapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MovieScore {
    @Id
    private Long id;
    @Column
    private double finalScore;
    @Column
    private double totalPossiblePoints;
    @Column
    private double actualPoints;

    public MovieScore() {
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public void setTotalPossiblePoints(double points) {
        totalPossiblePoints += points;
    }

    public void setActualPoints(double points) {
        actualPoints += points;
    }

    public void calculateFinalScore(){
        finalScore = actualPoints/totalPossiblePoints;
    }
}

