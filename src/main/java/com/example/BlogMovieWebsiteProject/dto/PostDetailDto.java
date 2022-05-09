package com.example.BlogMovieWebsiteProject.dto;

import com.example.BlogMovieWebsiteProject.model.ItemPosts;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
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
    private List<String> tags;
    private double yourRating;
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;
    private Integer visit;
    private boolean browser;
}
