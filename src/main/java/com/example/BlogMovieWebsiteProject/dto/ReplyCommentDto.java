package com.example.BlogMovieWebsiteProject.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class ReplyCommentDto {
    private String username;
    private String postId;
    private String commentId;
    private String replyContent;
    private String time;
    private String dformat;
    private String buttonId;
}
