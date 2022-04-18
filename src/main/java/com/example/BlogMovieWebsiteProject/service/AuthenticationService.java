package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.dto.UsersDto;
import com.example.BlogMovieWebsiteProject.model.Users;
import com.example.BlogMovieWebsiteProject.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService
{
    private final UsersRepository usersRepository;

    public boolean checkLogin(UsersDto usersDto)
    {
        return usersRepository.existsByUsernameAndPassword(usersDto.getUsername(), usersDto.getPassword());
    }
}
