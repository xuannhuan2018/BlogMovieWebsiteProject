package com.example.BlogMovieWebsiteProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Indexed;
import java.sql.Timestamp;

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

    private  String fullname;
    private Timestamp birthday;
    private String address;
    private String gender;
    private Timestamp created;
    private boolean status;

    private enum Role
    {
        member, admin
    }

    @Override
    public String toString()
    {
        return "userId =  " + id + "fullname = " + fullname;
    }
}
