package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.dto.ReplyCommentDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketsController {

    @MessageMapping("/comment/reply/content")//MapMessage is sent
    @SendTo("/post/comment/reply")//Subscribe
    public ReplyCommentDto test(@Payload ReplyCommentDto replyCommentDto){
        return replyCommentDto;
    }
}
