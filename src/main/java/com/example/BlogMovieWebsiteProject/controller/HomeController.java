package com.example.BlogMovieWebsiteProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex(){
        return "/views/index";
    }

}
