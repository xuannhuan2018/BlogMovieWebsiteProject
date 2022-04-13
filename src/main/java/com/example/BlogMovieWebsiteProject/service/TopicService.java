package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.repository.TopicRepository;
import com.example.BlogMovieWebsiteProject.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BlogMovieWebsiteProject.dto.TopicDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService
{
    @Autowired
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository)
    {
        this.topicRepository = topicRepository;
    }

    public Topic addTopic(TopicDto topicDTo) {
        Topic topic = new Topic(topicDTo.getId(), topicDTo.getName());
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopic() {
        return topicRepository.findAll();
    }

    public  List<Topic> getByName(String name)
    {
        return topicRepository.findByName(name);
    }

    public void deleteTopic(Topic topic)
    {
        topicRepository.delete(topic);
    }
}
