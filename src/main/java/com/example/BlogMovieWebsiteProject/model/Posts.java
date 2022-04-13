package com.example.BlogMovieWebsiteProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "postsmovie")
public class Posts
{
    @Id
    private String Id;

    private String username;
    private String title;
    private String headerImg;
    private String name;
    private String description;
    private List<ItemPosts> itemPosts;
    private double IMDb;
    private double myRating;
    private List<String> tags;
    private Instant created;
    private boolean browser;
}
