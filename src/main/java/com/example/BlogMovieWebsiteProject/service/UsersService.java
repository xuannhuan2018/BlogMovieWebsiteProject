package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.dto.UsersDto;
import com.example.BlogMovieWebsiteProject.model.Roles;
import com.example.BlogMovieWebsiteProject.repository.UsersRepository;
import com.example.BlogMovieWebsiteProject.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import java.util.Date;


@Service
@Transactional
@AllArgsConstructor
public class UsersService
{
    @Autowired
    private UsersRepository usersRepository;

    public Users createAccount(UsersDto usersDto, String username, String password, String email)
    {
        //Set up username, password, email for usersDto
        usersDto.setUsername(username);
        usersDto.setPassword(password);
        usersDto.setEmail(email);
        //Get current time
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        //Initialize User object
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        users.setEmail(usersDto.getEmail());
        users.setCreated(timestamp.toString());
        users.setStatus(true);
        users.setRole(Roles.member);
        return usersRepository.save(users);
    }

    public boolean existByUsername(String username){
        Users users = usersRepository.findUsersByUsername(username);
        boolean exist;
        exist = users != null;
        return exist;
    }

    public boolean existByUsernameAndPassword(String username, String password){
        Users users = usersRepository.findUsersByUsernameAndAndPassword(username, password);
        boolean exist;
        exist = users != null;
        return exist;
    }
    public Users findUserByUsername(String username){
        return usersRepository.findUsersByUsername(username);
    }

    public void saveUser(UsersDto usersDto, String username){
        Users users = usersRepository.findUsersByUsername(username);
        users.setFullName(usersDto.getFullName());
        users.setAddress(usersDto.getAddress());
        users.setEmail(usersDto.getEmail());
        users.setGender(usersDto.getGender());
        users.setPassword(usersDto.getPassword());
        usersRepository.save(users);
    }
}
