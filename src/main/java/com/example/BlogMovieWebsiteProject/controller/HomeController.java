package com.example.BlogMovieWebsiteProject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getIndex(){
        return "/views/index";
    }

    @GetMapping("/test/search")
    @ResponseBody
    public String testSearch(@RequestParam(value = "keyword", required = false) String keyword){
        return keyword;
    }

}
