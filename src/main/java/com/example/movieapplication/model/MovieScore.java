package com.example.movieapplication.model;

import javax.persistence.*;

@Entity
@Table(name="movie_score")
public class MovieScore {

    @Id
    @Column(name="score_id")
    @GeneratedValue
    private Long id;

    @Column
    private double finalScore;

    @Column
    private double totalPossiblePoints;

    @Column
    private double totalActualPoints;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_score_join", nullable = false)
    private Movie movie;

    public MovieScore() {
        this.finalScore = 0;
    }

    public MovieScore(Movie movie){
        this.finalScore = 0;
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public double getTotalPossiblePoints() {
        return totalPossiblePoints;
    }

    public void setTotalPossiblePoints(double totalPossiblePoints) {
        this.totalPossiblePoints = totalPossiblePoints;
    }

    public double getTotalActualPoints() {
        return totalActualPoints;
    }

    public void setTotalActualPoints(double totalActualPoints) {
        this.totalActualPoints = totalActualPoints;
    }

    public void calculateFinalScore() {
        this.finalScore = (totalActualPoints/totalPossiblePoints) * 100;
    }
}
