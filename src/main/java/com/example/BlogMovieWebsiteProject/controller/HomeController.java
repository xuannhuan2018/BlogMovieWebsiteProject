package com.example.BlogMovieWebsiteProject.controller;


import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.model.Posts;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("listTopThreePosts", postsService.listTopThreePostsHighestViews());
        return "/views/index";
    }

    @GetMapping("/post/{postId}")
    public String viewDetailPost(@PathVariable(name = "postId")String postId,
                                 Model model, HttpServletRequest request){
        model.addAttribute("listCategory", categoryService.listCategory());
        model.addAttribute("postDetail", postsService.viewDetailPost(postId, request));
        return "views/user/post/view-detail-post";
    }
    @GetMapping("/home/all-post")
    public String listAllPost(Model model){
        model.addAttribute("listCategory", categoryService.listCategory());
        model.addAttribute("listAllPost", postsService.listAllPost());
        model.addAttribute("listTopThreePosts", postsService.listTopThreePostsHighestViews());
        return "/views/user/post/list-post";
    }


    @GetMapping("/home/search")
    public String showSearchForm(Model model){
        return "/views/user/search";
    }

    @GetMapping("/home/search/{searchType}/{keyword}")
    public String testSearch(@RequestParam(value = "keyword", required = false) String keyword,
                             @RequestParam(value = "searchType", required = false) String searchType,
                             Model model, HttpServletRequest request){
        if(searchType == null){
            searchType = "all";
        }
        List<PostDetailDto> postDetailDtoList = postsService.search(keyword, searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        model.addAttribute("listSearchResult", postDetailDtoList);
        return "/views/user/search";
    }
}
