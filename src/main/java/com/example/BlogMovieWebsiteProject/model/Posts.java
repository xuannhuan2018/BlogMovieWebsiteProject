package com.example.BlogMovieWebsiteProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

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

    private long number;

    private String username;
    private String category;
    private String title;
    private String imgHeader;
    private String description;
    private List<ItemPosts> itemPost;
    private double IMDb;
    private double yourRating;
    private String created;
    private boolean browser;
}
