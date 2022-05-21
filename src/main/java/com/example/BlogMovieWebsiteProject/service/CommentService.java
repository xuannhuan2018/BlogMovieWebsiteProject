package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.model.Comment;
import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.model.ResponseComment;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import net.bytebuddy.utility.RandomString;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private PostsRepository postsRepository;

    public void addComment(String postId, String username, String message){
        Optional<Posts> opPosts = postsRepository.findById(postId);
        Posts posts = opPosts.get();
        Date date = new Date();
        Comment comment = new Comment();
        List<ResponseComment> responseCommentList = new ArrayList<>();
        comment.setCommentId(this.createCommentId());
        comment.setUsername(username);
        comment.setCreateTime(date);
        comment.setContent(message);
        comment.setResponseCommentList(responseCommentList);
        List<Comment> commentList = posts.getCommentList();
        if(commentList == null){
            commentList = new ArrayList<>();
        }
        commentList.add(comment);
        posts.setCommentList(commentList);
        postsRepository.save(posts);
    }
    public void replyComment(String postId, String username, String replyContent, String commentId){
        Optional<Posts> opPosts = postsRepository.findById(postId);
        Posts posts = opPosts.get();
        Date date = new Date();
        List<Comment> commentList = posts.getCommentList();
        Comment commentResult = new Comment();
        for (Comment comment:commentList) {
                if(comment.getCommentId().equals(commentId)){
                commentResult = comment;
                break;
            }
        }
        List<ResponseComment> responseCommentList = commentResult.getResponseCommentList();
        ResponseComment responseComment = new ResponseComment();
        responseComment.setUsername(username);
        responseComment.setReplyContent(replyContent);
        responseComment.setReplyTime(date);
        responseCommentList.add(responseComment);
        postsRepository.save(posts);
    }
    public String createCommentId() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        //Get date-month-year to generate commentId
        String commentId = timestamp.toString().substring(0, 10);
        commentId = commentId.replace("-", "");
        commentId = commentId + RandomString.make(5);

        return commentId;
    }
}
