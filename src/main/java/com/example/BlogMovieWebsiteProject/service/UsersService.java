package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.dto.UsersDto;
import com.example.BlogMovieWebsiteProject.model.Roles;
import com.example.BlogMovieWebsiteProject.repository.UsersRepository;
import com.example.BlogMovieWebsiteProject.model.Users;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.search.DocValueFormat;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.BlogMovieWebsiteProject.dto.TopicDto;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.util.Locale;

@Service
@AllArgsConstructor
public class UsersService
{
    private final UsersRepository usersRepository;


    private final ConvertService convertService;

    public Users createAccount(UsersDto usersDto)
    {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        users.setEmail(usersDto.getEmail());
        users.setCreated(convertService.DateTimeToString());
        users.setStatus(false);
        users.setRole(Roles.member);

        return usersRepository.save(users);
    }
}
