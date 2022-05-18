package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.service.CategoryService;
import com.example.BlogMovieWebsiteProject.service.HomeService;
import com.example.BlogMovieWebsiteProject.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home/search/category/get/{category}")
    public String testSearch(@PathVariable(name = "category") String category,
                             Model model, HttpServletRequest request){
        List<PostDetailDto> postDetailDtoList = postsService.search(category, "category");
        model.addAttribute("keyword", category);
        model.addAttribute("searchType", "category");
        model.addAttribute("listSearchResult", postDetailDtoList);
        return "views/user/search";
    }
}
