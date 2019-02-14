package com.example.movieapplication.model;

public class Movie {
    private String title;
    private String description;
    private String genre;
    private MovieScore movieScore;

    public Movie(MovieScore movieScore) {
        this.movieScore = movieScore;
    }
}
