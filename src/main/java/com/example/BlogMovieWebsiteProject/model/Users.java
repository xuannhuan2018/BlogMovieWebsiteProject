package com.example.BlogMovieWebsiteProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "users")

public class Users
{
    @Id
    private String id;

    private String username;
    private String password;
    private  String email;

    private  String full_name;
    private Timestamp birthday;
    private String address;
    private String gender;
    private Instant created;
    private boolean status;
    private Roles role;

    @Override
    public String toString()
    {
        return "User ID =  " + id + "Full name = " + full_name;
    }
}
