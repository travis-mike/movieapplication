package com.example.movieapplication.controller;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.repository.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

    @Autowired
    private Movies movies;

    @RequestMapping("movies/{movie}")
    public String getMoviePage (Model model, @PathVariable ("movie") String movie) {
        Movie foundMovie = movies.findByTitle(movie);
        //Need to write an exception if movie is not found
        model.addAttribute("movie", foundMovie);
        return "movie-page";
    }
}
