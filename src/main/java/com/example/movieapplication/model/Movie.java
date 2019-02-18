package com.example.movieapplication.model;

import javax.persistence.*;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String genre;

    @Transient
    private MovieScore movieScore;

    public Movie(MovieScore movieScore) {
        this.movieScore = movieScore;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MovieScore getMovieScore() {
        return movieScore;
    }

    public void setMovieScore(MovieScore movieScore) {
        this.movieScore = movieScore;
    }
}
