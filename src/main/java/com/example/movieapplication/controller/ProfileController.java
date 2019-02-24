package com.example.movieapplication.controller;

import com.example.movieapplication.model.Movie;
import com.example.movieapplication.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    //Need to figure out how to iterate through collection without causing lazyloader error

        @RequestMapping("/profile")
        public String secureHomePage(Model model) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user == null) {
                return "redirect:/login";
            }
            model.addAttribute("user", user);
            List<Movie> testList = new ArrayList<>();
            testList = user.getUserMovieList();
            model.addAttribute("testList", testList);
                return "profile";
        }
    }

