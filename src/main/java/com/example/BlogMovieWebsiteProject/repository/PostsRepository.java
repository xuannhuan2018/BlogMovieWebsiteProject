package com.example.BlogMovieWebsiteProject.repository;

import com.example.BlogMovieWebsiteProject.model.Posts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface PostsRepository extends ElasticsearchRepository<Posts, String>
{
    Posts findTopByOrderByNumberDesc();
}
