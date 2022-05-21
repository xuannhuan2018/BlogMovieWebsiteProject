package com.example.BlogMovieWebsiteProject.repository;


import com.example.BlogMovieWebsiteProject.model.Posts;
import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;

public interface PostsCustomRepository {
    List<SearchHit<Posts>> findListPostInHome();
    List<SearchHit<Posts>> findAllPost();
    List<SearchHit<Posts>> searchAll(String keyword);
    List<SearchHit<Posts>> searchByType(String keyword, String searchType);
    List<SearchHit<Posts>> findThreePostsMaxViews();
    List<SearchHit<Posts>> findThreeRelatedPosts(String category);
}
