package com.example.BlogMovieWebsiteProject.repository;

import com.example.BlogMovieWebsiteProject.model.Posts;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends ElasticsearchRepository<Posts, String>, PostsCustomRepository {
    List<Posts> findAll();

    List<Posts> findAllByUsername(String username);

//    @Query("{" +
//            "   \"bool\": {" +
//            "     \"should\": [" +
//            "       {" +
//            "         \"query_string\": {" +
//            "           \"query\": \"*?0*\"," +
//            "           \"default_operator\": \"AND\",  " +
//            "           \"fields\": [\"itemPost.text\", \"title\", \"description\", \"category\"]" +
//            "         }" +
//            "       }," +
//            "       {" +
//            "         \"multi_match\" : {" +
//            "           \"type\": \"best_fields\"," +
//            "           \"query\": \"?0\"," +
//            "           \"operator\": \"AND\", " +
//            "           \"fields\": [\"itemPost.text\", \"title\", \"description\", \"category\"]," +
//            "           \"fuzziness\": 1" +
//            "         }" +
//            "       }]" +
//            "   }" +
//            " }")
//    List<Posts> testSearch(String keyword);
}
