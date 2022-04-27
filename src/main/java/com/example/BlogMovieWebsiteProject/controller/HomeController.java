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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String getIndex(Model model){
        List<PostDetailDto> postDetailDtoList = homeService.listPostInHome();
        model.addAttribute("listCategory", categoryService.listCategory());
        model.addAttribute("listPostInHome", postDetailDtoList);
        return "/views/index";
    }

    @GetMapping("/post/{postId}")
    public String viewDetailPost(@PathVariable(name = "postId")String postId,
                                 Model model){
        model.addAttribute("listCategory", categoryService.listCategory());
        model.addAttribute("postDetail", postsService.viewDetailPost(postId));
        return "views/user/post/view-detail-post";
    }

    @GetMapping("/test/search")
    @ResponseBody
    public String testSearch(@RequestParam(value = "keyword", required = false) String keyword){
        return keyword;
    }

}
