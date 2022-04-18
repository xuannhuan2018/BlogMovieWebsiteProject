package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.PostsDto;
import com.example.BlogMovieWebsiteProject.service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/post")
public class PostsController
{
    @Autowired
    public PostsService postsService;

    @PostMapping("new")
    public String createPosts(@RequestBody PostsDto postsDto) throws IOException {
        postsService.createPosts(postsDto, "woman");
        return "Successfully";
    }
}

