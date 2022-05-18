package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.dto.PostsDto;
import com.example.BlogMovieWebsiteProject.model.Users;
import com.example.BlogMovieWebsiteProject.service.CategoryService;
import com.example.BlogMovieWebsiteProject.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/user/post")
public class PostsController
{
    @Autowired
    private PostsService postsService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add-new-post")
    public String showAddNewPostView(Model model){
        model.addAttribute("post", new PostsDto());
        model.addAttribute("listCategory", categoryService.listCategory());
        return "views/user/post/new-post";
    }


    @PostMapping("/add")
    public String createPost(@ModelAttribute("post") PostsDto postsDto, HttpSession session,
                             RedirectAttributes ra) throws IOException {
        Users user = (Users) session.getAttribute("user");
        String username = user.getUsername();
        String postId = "";
        try {
            postId = postsService.createPosts(postsDto, username);
            ra.addFlashAttribute("alert", "Thêm bài viết " + postsDto.getTitle() + " thành công!");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return "redirect:/user/post/" + postId;
    }

    @GetMapping("/{username}/list")
    public String viewListPostOfUser(@PathVariable(name = "username") String username,
                                     Model model){
        List<PostDetailDto> postsList = postsService.listPostByUsername(username);
        model.addAttribute("listPostOfUser", postsList);
        return "views/user/post/list-my-post";
    }

    @GetMapping("/{postId}")
    public String viewPostDetail(@PathVariable(name = "postId")String postId,
                                 Model model, HttpServletRequest request){
        model.addAttribute("postDetail", postsService.viewDetailPost(postId, request));
        return "views/user/post/view-detail-post-of-user";
    }

}

