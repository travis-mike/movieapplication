package com.example.movieapplication.model;

import java.util.ArrayList;
import java.util.List;

public class MovieListWrapper {

    private List<Long> movieIdList = new ArrayList<>();

    public MovieListWrapper (List<Long> movieIdList) {
        this.movieIdList = movieIdList;
    }

    public List<Long> getMovieIdList() {
        return movieIdList;
    }

    public void setMovieIdList(List<Long> movieIdList) {
        this.movieIdList = movieIdList;
    }
}



