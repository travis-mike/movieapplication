package com.example.movieapplication.model;

import java.util.ArrayList;
import java.util.List;

public class MovieListWrapper {
    List<Movie> movieList = new ArrayList<>();

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
