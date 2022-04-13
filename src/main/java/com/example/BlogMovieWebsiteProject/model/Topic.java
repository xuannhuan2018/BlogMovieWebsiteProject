package com.example.BlogMovieWebsiteProject.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "topicmovie")
public class Topic
{
    @Id
    private String Id;

    private String name;
}
