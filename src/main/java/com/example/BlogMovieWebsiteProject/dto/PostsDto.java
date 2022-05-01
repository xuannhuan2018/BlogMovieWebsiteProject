package com.example.BlogMovieWebsiteProject.dto;

import com.example.BlogMovieWebsiteProject.model.ItemPosts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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
    private List<String> category;
    private String title;
    private MultipartFile imgHeader;
    private String description;
    private List<String> tags;
    private List<ItemPostsDto> itemPost;
    private double yourRating;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private boolean browser;
}
