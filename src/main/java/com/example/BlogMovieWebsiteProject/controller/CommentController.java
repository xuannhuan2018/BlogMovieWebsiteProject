package com.example.BlogMovieWebsiteProject.controller;


import com.example.BlogMovieWebsiteProject.model.Users;
import com.example.BlogMovieWebsiteProject.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private CommentService CommentService;

    @PostMapping("/post/{postId}/comment")
    public String addCommentToPost(@PathVariable(name = "postId") String postId,
                                   @RequestParam(value = "message", required = false) String message,
                                   HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            String username = user.getUsername();
            CommentService.addComment(postId, username, message);
        }
        return "redirect:/post/" + postId;
    }

    @PostMapping("/post/comment/reply")
    @ResponseBody
    public void replyComment(@RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "postId", required = false) String postId,
                             @RequestParam(value = "commentId", required = false) String commentId,
                             @RequestParam(value = "replyContent", required = false) String replyContent) {
        CommentService.replyComment(postId, username, replyContent, commentId);
    }
}
