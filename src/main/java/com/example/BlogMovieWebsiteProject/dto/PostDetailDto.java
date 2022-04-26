package com.example.BlogMovieWebsiteProject.dto;

import com.example.BlogMovieWebsiteProject.model.ItemPosts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDetailDto {
    @Id
    private String Id;

    private long number;

    private String username;
    private List<String> category;
    private String title;
    private String imgHeader;
    private String description;
    private List<ItemPosts> itemPost;
    private double IMDb;
    private double yourRating;
    private String created;
    private boolean browser;
}
