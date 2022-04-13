package com.example.BlogMovieWebsiteProject.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TopicDto
{
    @Id
    private String Id;

    private String name;
}
