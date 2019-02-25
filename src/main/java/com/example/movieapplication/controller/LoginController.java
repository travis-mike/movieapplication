package com.example.movieapplication.controller;

import com.example.movieapplication.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String secureHomePage(Model model) {
        return "index";
    }
}
