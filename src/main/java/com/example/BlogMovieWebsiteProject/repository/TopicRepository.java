package com.example.BlogMovieWebsiteProject.repository;

import com.example.BlogMovieWebsiteProject.model.Topic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends ElasticsearchRepository<Topic, String>
{
    List<Topic> findAll();
    List<Topic> findByName(String name);
}
