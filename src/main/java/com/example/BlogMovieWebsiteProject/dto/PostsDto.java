package com.example.BlogMovieWebsiteProject.dto;

import com.example.BlogMovieWebsiteProject.model.ItemPosts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsDto
{
    @Id
    private String Id;

    private long number;

    private String username;
    private String category;
    private String title;
    private MultipartFile imgHeader;
    private String description;
    private List<ItemPostsDto> itemPostsDto;
    private double IMDb;
    private double yourRating;
    private String created;
    private boolean browser;
}
