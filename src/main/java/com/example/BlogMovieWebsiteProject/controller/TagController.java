package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagController {
    @Autowired
    private PostsService postsService;

    @GetMapping("/home/search/tags/get/{tags}")
    public String testSearch(@PathVariable(name = "tags") String tags,
                             Model model){
        List<PostDetailDto> postDetailDtoList = postsService.search(tags, "tags");
        model.addAttribute("keyword", tags);
        model.addAttribute("searchType", "tags");
        model.addAttribute("listSearchResult", postDetailDtoList);
        return "views/user/search";
    }
}
