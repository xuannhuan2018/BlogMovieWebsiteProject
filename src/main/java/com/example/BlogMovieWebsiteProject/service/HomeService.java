package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHit;
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
        List<SearchHit<Posts>> postsSearchHitList = postsRepository.findListPostInHome();
        for (SearchHit<Posts> postsSearchHit : postsSearchHitList) {
            Posts posts = postsSearchHit.getContent();
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
        return postDetailDtoList;
    }
}
