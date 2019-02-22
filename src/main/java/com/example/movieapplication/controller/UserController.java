package com.example.movieapplication.controller;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.model.User;
import com.example.movieapplication.model.UserTransporter;
import com.example.movieapplication.repository.Movies;
import com.example.movieapplication.repository.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private Movies movies;
    private UserTransporter transporter = new UserTransporter();

    public UserController(Users users, PasswordEncoder passwordEncoder, Movies movies) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.movies = movies;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        transporter.setUser(user);
        return "redirect:register/movies";
    }

    @GetMapping("register/movies")
    public String userSelectsFilms(Model model) {
        List<Movie> movieStarters = new ArrayList<>();
        movieStarters.add(movies.findById(1L).get());
        movieStarters.add(movies.findById(2L).get());
        model.addAttribute("movieSignUpList", movieStarters);
        return "signup-movieselection";
    }

    @PostMapping("register/movies")
    public String userFilmsPost(@RequestParam ("movieIdList") Long[] movieIdList) {
        //List<Movie> movieStarterList = new ArrayList<>();
        //List<Long> movieIdStarter = new ArrayList<>(Arrays.asList(movieIdList));
        User user = transporter.getUser();
        Movie testMovie = movies.findById(movieIdList[0]).get();
        Movie testMovie2 = movies.findById(movieIdList[1]).get();
        System.out.println(testMovie.getDescription());
        user.setUserMovieList(testMovie);
        user.setUserMovieList(testMovie2);
        users.save(user);
        return "redirect:/login";
    }
}


