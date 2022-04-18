package com.example.BlogMovieWebsiteProject.controller;

import com.example.BlogMovieWebsiteProject.dto.UsersDto;
import com.example.BlogMovieWebsiteProject.model.Users;
import com.example.BlogMovieWebsiteProject.repository.TopicRepository;
import com.example.BlogMovieWebsiteProject.service.AuthenticationService;
import com.example.BlogMovieWebsiteProject.service.TopicService;
import com.example.BlogMovieWebsiteProject.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BlogMovieWebsiteProject.dto.TopicDto;
import com.example.BlogMovieWebsiteProject.model.Topic;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UsersController
{
    @Autowired
    public UsersService usersService;

    @Autowired
    public AuthenticationService authenticationService;

    @PostMapping("add")
    public String saveUser(@RequestBody UsersDto usersDto)
    {
        usersService.createAccount(usersDto);

        return "Đã đăng ký thành công tài khoản có tên " + usersDto.getPassword().toString();
    }

    @GetMapping("login")
    public boolean checkLogin(@RequestBody UsersDto usersDto)
    {
        return authenticationService.checkLogin(usersDto);
    }

}
