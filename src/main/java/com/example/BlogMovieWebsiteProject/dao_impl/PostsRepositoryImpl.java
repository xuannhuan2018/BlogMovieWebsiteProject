package com.example.BlogMovieWebsiteProject.dao_impl;

import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.PostsCustomRepository;
import org.elasticsearch.index.query.QueryBuilder;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PostsRepositoryImpl implements PostsCustomRepository {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<SearchHit<Posts>> findListPostInHome() {
        QueryBuilder matchQuery = matchQuery("browser", true);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery).withSort(SortBuilders.fieldSort("created").order(SortOrder.DESC)).withMaxResults(3).build();
        SearchHits<Posts> postsSearchHits = elasticsearchOperations.search(searchQuery, Posts.class);
        return postsSearchHits.getSearchHits();
    }
}

