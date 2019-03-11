package com.example.movieapplication.controller;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.model.User;
import com.example.movieapplication.repository.Users;
import com.example.movieapplication.service.UserDetailsLoader;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private Users users;

    //need to figure out how to iterate through collection without causing lazyloader error

        @RequestMapping("/profile")
        public String secureHomePage(Model model) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user == null) {
                return "redirect:/login";
            }
            UserDetailsLoader userDetailsLoader = new UserDetailsLoader(users);
            User movieUser = userDetailsLoader.loadUserWithMovieList(user.getUsername());
            model.addAttribute("movieList", movieUser.getUserMovieList());
                return "profile";
        }
    }

