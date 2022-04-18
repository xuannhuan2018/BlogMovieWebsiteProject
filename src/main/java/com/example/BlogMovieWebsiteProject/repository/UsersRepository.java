package com.example.BlogMovieWebsiteProject.repository;

import com.example.BlogMovieWebsiteProject.model.Users;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends ElasticsearchRepository<Users, String>
{
    boolean existsByUsernameAndPassword(String username, String password);
}
