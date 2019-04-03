package com.example.movieapplication.controller;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.model.MovieScore;
import com.example.movieapplication.model.User;
import com.example.movieapplication.service.MovieService;
import com.example.movieapplication.service.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//RESTFUL API

@RestController
@RequestMapping("/api/v1")
public class UserDBController {

    @Autowired
    private final MovieService movieService;
    private final UserDetailsLoader userDetailsLoader;

    public UserDBController(MovieService movieService, UserDetailsLoader userDetailsLoader) {
        this.movieService = movieService;
        this.userDetailsLoader = userDetailsLoader;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> findById(@PathVariable Long movieId) {
        Optional<Movie> movieOptional = movieService.findById(movieId);
        if (!movieOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(movieOptional.get());
    }

    @PatchMapping("/{username}/add/{movieId}")
    public ResponseEntity<User> addMovieToUserFavorites(@PathVariable String username, @PathVariable Long movieId) {
        User user = userDetailsLoader.loadUserWithMovieList(username);
        if (user == null) {
            ResponseEntity.badRequest().build();
        }
        Optional<Movie> movieOptional = movieService.findById(movieId);
        Movie foundMovie = movieOptional.get();
        user.addToUserMovieList(foundMovie);
        return ResponseEntity.ok(userDetailsLoader.saveUser(user));
    }

    @PatchMapping("/{movieId}/rating")
    public ResponseEntity<?> updateRating(@PathVariable Long movieId, @RequestBody MovieScore updatingScore) {
        Optional<Movie>movieOptional = movieService.findById(movieId);
        if (!movieOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        Movie movie = movieOptional.get();
        MovieScore movieScore = movie.getMovieScore();
        if (updatingScore.getTotalPossiblePoints() != 0) movieScore.setTotalPossiblePoints(movieScore.getTotalPossiblePoints() + updatingScore.getTotalPossiblePoints());
        if (updatingScore.getTotalActualPoints() != 0) movieScore.setTotalActualPoints(movieScore.getTotalActualPoints() + updatingScore.getTotalActualPoints());
        movieScore.calculateFinalScore();
        movie.setMovieScore(movieScore);
        movieService.save(movie);
        return ResponseEntity.ok("Rating saved.");
    }

    @PatchMapping("/test/{movieId}")
    public ResponseEntity<Movie> update(@PathVariable Long movieId, @RequestBody Movie updatingMovie) {
        Optional<Movie> movieOptional = movieService.findById(movieId);
        if (!movieOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        Movie movie = movieOptional.get();
        if (updatingMovie.getTitle() != null) movie.setTitle(updatingMovie.getTitle());

        return ResponseEntity.ok(movieService.save(movie));
    }

}



