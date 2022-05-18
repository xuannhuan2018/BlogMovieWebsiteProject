package com.example.BlogMovieWebsiteProject.dao_impl;

import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.PostsCustomRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;

import org.elasticsearch.index.query.QueryBuilders;
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

import static org.elasticsearch.index.query.QueryBuilders.*;


@Repository
public class PostsRepositoryImpl implements PostsCustomRepository {
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<SearchHit<Posts>> findListPostInHome() {
        QueryBuilder matchQuery = matchQuery("browser", true);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery)
                .withSort(SortBuilders.fieldSort("created")
                        .order(SortOrder.DESC))
                .withMaxResults(3).build();
        SearchHits<Posts> postsSearchHits = elasticsearchOperations.search(searchQuery, Posts.class);
        return postsSearchHits.getSearchHits();
    }

    @Override
    public List<SearchHit<Posts>> findThreePostsMaxViews()
    {
        QueryBuilder matchQuery = matchQuery("browser", true);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery)
                .withSort(SortBuilders.fieldSort("visit")
                        .order(SortOrder.DESC))
                .withMaxResults(3).build();
        SearchHits<Posts> postsSearchHits = elasticsearchOperations.search(searchQuery, Posts.class);
        return postsSearchHits.getSearchHits();
    }
    @Override
    public List<SearchHit<Posts>> findAllPost() {
        QueryBuilder matchQuery = matchQuery("browser", true);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery)
                .withSort(SortBuilders.fieldSort("created")
                        .order(SortOrder.DESC))
                .build();
        SearchHits<Posts> postsSearchHits = elasticsearchOperations.search(searchQuery, Posts.class);
        return postsSearchHits.getSearchHits();
    }

    @Override
    public List<SearchHit<Posts>> searchAll(String keyword) {
        QueryBuilder boolQuery = boolQuery()
                .must(matchQuery("browser", true))
                .must(boolQuery()
                        .should(queryStringQuery("*" + keyword + "*")
                                .field("title")
                                .field("category")
                                .field("description")
                                .field("itemPost.text")
                                .field("tags")
                                .field("username")
                                .defaultOperator(Operator.AND))
                        .should(multiMatchQuery(keyword)
                                .field("title")
                                .field("category")
                                .field("description")
                                .field("itemPost.text")
                                .field("tags")
                                .field("username")
                                .type(MultiMatchQueryBuilder.Type.BEST_FIELDS)
                                .operator(Operator.AND)
                                .fuzziness(Fuzziness.ONE)));
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery).build();
        SearchHits<Posts> postsSearchHits = elasticsearchOperations.search(searchQuery, Posts.class);
        return postsSearchHits.getSearchHits();
    }

    @Override
    public List<SearchHit<Posts>> searchByType(String keyword, String searchType) {
        QueryBuilder boolQuery = boolQuery()
                .must(matchQuery("browser", true))
                .must(multiMatchQuery(keyword)
                        .field(searchType));
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery).build();
        SearchHits<Posts> postsSearchHits = elasticsearchOperations.search(searchQuery, Posts.class);
        return postsSearchHits.getSearchHits();
    }
}

