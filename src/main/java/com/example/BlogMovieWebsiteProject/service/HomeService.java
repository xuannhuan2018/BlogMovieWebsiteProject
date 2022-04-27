package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    private PostsRepository postsRepository;

    //Get the latest 3 posts by created date and approved
    public List<PostDetailDto> listPostInHome(){
        List<PostDetailDto> postDetailDtoList = new ArrayList<>();
        List<Posts> postsList = postsRepository.findAllByBrowserOrderByCreatedDesc(true);
        if(postsList == null){
            System.out.println("Chưa có bài nào được duyệt");
        }
        else {
            for (int i = 0; i < 3; i++) {
                Posts posts = postsList.get(i);
                PostDetailDto postDetailDto = new PostDetailDto();
                postDetailDto.setId(posts.getId());
                postDetailDto.setTitle(posts.getTitle());
                postDetailDto.setUsername(posts.getUsername());
                postDetailDto.setCreated(posts.getCreated());
                postDetailDto.setDescription(posts.getDescription());
                postDetailDto.setCategory(posts.getCategory());
                postDetailDto.setImgHeader(posts.getImgHeader());
                postDetailDtoList.add(postDetailDto);
            }
        }
        return postDetailDtoList;
    }
}
