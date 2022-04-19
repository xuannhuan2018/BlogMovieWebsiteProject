package com.example.BlogMovieWebsiteProject.repository;

import com.example.BlogMovieWebsiteProject.model.Posts;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends ElasticsearchRepository<Posts, String>
{
    List<Posts> findAll();
}
