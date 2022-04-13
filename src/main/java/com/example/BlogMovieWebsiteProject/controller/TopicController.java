package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.repository.TopicRepository;
import com.example.BlogMovieWebsiteProject.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BlogMovieWebsiteProject.dto.TopicDto;
import com.example.BlogMovieWebsiteProject.model.Topic;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/topic")
public class TopicController
{
    @Autowired
    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService)
    {
        this.topicService = topicService;
    }

    @PostMapping("add")
    public ResponseEntity<Topic> addProduct(@RequestBody TopicDto topicDto)
    {
        Topic topic = topicService.addTopic(topicDto);
        return ResponseEntity.ok(topic);
    }

    @GetMapping("getlist")
    public ResponseEntity<List<Topic>> getAllTopic()
    {
        return ResponseEntity.ok(topicService.getAllTopic());
    }

    @GetMapping("findByName/{name}")
    public List<Topic> findByName(@PathVariable String name)
    {
        return topicService.getByName(name);
    }
}
