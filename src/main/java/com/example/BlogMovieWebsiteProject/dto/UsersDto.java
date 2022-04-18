package com.example.BlogMovieWebsiteProject.dto;

import com.example.BlogMovieWebsiteProject.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto
{
    @Id
    private String id;

    private String username;
    private String password;
    private String email;
    private String created;
    private boolean status;
    private Roles role;

}
