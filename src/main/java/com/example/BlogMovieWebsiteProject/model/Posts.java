package com.example.BlogMovieWebsiteProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "posts-movie")
public class Posts  {
    @Id
    private String Id;

    @Field(type = FieldType.Long)
    private long number;
    @Field(type = FieldType.Text)
    private String username;

    private List<String> category;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String imgHeader;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Text)
    private List<String> tags;

    private List<ItemPosts> itemPost;
    @Field(type = FieldType.Text)
    private double yourRating;

    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private boolean browser;
}
