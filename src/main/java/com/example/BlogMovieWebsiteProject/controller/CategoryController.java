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
public class CategoryController {
    @Autowired
    private PostsService postsService;

    @GetMapping("/home/search/category/get/{category}")
    public String testSearch(@PathVariable(name = "category") String category,
                             Model model) {
        List<PostDetailDto> postDetailDtoList = postsService.search(category, "category");
        model.addAttribute("keyword", category);
        model.addAttribute("searchType", "category");
        model.addAttribute("listSearchResult", postDetailDtoList);
        return "views/user/search";
    }
}
