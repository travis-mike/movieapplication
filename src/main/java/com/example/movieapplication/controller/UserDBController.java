package com.example.movieapplication.controller;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.model.User;
import com.example.movieapplication.repository.Movies;
import com.example.movieapplication.repository.Users;
import com.example.movieapplication.service.MovieService;
import com.example.movieapplication.service.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserDBController {

    @Autowired
    private final MovieService movieService;
    private final UserDetailsLoader userDetailsLoader;
    private Users users;
    private Movies movies;

    public UserDBController(MovieService movieService, UserDetailsLoader userDetailsLoader) {
        this.movieService = movieService;
        this.userDetailsLoader = userDetailsLoader;
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<?> saveResource(@RequestBody User user,
                                          @PathVariable("id") String id) {
        Long newId = Long.parseLong(id);
        User newUser = new User(newId, "name12345", "name123456", "test@testers.com");
        users.save(newUser);
        return ResponseEntity.ok("resource saved");
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> findById(@PathVariable Long movieId) {
        Optional<Movie> movieOptional = movieService.findById(movieId);
        if (!movieOptional.isPresent()) {
            System.out.println("This shit didn't work");
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



