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

@SuppressWarnings("Duplicates")
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
    public ResponseEntity<?> addMovieToUserFavorites(@PathVariable String username, @PathVariable Long movieId) {
        User user = userDetailsLoader.loadUserWithMovieList(username);
        if (user == null) {
            ResponseEntity.badRequest().build();
        }
        Optional<Movie> movieOptional = movieService.findById(movieId);
        Movie foundMovie = movieOptional.get();
        user.addToUserMovieList(foundMovie);
        userDetailsLoader.saveUser(user);
        return ResponseEntity.ok("Movie saved to user list.");
    }

    @PatchMapping("/{movieId}/rating/{movieGenreMatch}")
    public ResponseEntity<?> updateRating(@PathVariable Long movieId, @PathVariable boolean movieGenreMatch, @RequestBody MovieScore updatingScore) {
        Optional<Movie>movieOptional = movieService.findById(movieId);
        if (!movieOptional.isPresent()) {
            ResponseEntity.badRequest().build();
        }

        Movie movie = movieOptional.get();
        MovieScore movieScore = movie.getMovieScore();
        if (updatingScore.getTotalPossiblePoints() != 0) movieScore.setTotalPossiblePoints(movieScore.getTotalPossiblePoints() + updatingScore.getTotalPossiblePoints());
        if (updatingScore.getTotalActualPoints() != 0) movieScore.setTotalActualPoints(movieScore.getTotalActualPoints() + updatingScore.getTotalActualPoints());
        if (movieGenreMatch) {
            if (updatingScore.getTotalPossibleGenrePoints() != 0) movieScore.setTotalPossibleGenrePoints(movieScore.getTotalPossibleGenrePoints() + updatingScore.getTotalPossibleGenrePoints());
            if (updatingScore.getTotalActualGenrePoints() != 0) movieScore.setTotalActualGenrePoints(movieScore.getTotalActualGenrePoints() + updatingScore.getTotalActualGenrePoints());
        }
        movieScore.calculateFinalScore();
        if (movieGenreMatch) {
            movieScore.calculateFinalGenreScore();
        }
        movie.setMovieScore(movieScore);
        movieService.save(movie);
        return ResponseEntity.ok("Movie rating saved.");
    }

//    @PatchMapping("/{movieId}/rating/")
//    public ResponseEntity<?> updateGenreRating(@PathVariable Long movieId, @PathVariable boolean movieGenreMatch, @RequestBody MovieScore updatingGenreScore) {
//        Optional<Movie>movieOptional = movieService.findById(movieId);
//        if (!movieOptional.isPresent()) {
//            ResponseEntity.badRequest().build();
//        }
//
//        Movie movie = movieOptional.get();
//        MovieScore movieScore = movie.getMovieScore();
//        if (updatingGenreScore.getTotalPossibleGenrePoints() != 0) movieScore.setTotalPossibleGenrePoints(movieScore.getTotalPossibleGenrePoints() + updatingGenreScore.getTotalPossibleGenrePoints());
//        if (updatingGenreScore.getTotalActualGenrePoints() != 0) movieScore.setTotalActualGenrePoints(movieScore.getTotalActualGenrePoints() + updatingGenreScore.getTotalActualGenrePoints());
//        movieScore.calculateFinalGenreScore();
//        movie.setMovieScore(movieScore);
//        movieService.save(movie);
//        return ResponseEntity.ok("Genre rating saved.");
//    }

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



