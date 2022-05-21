package com.example.BlogMovieWebsiteProject;

import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;


@SpringBootTest
class BlogMovieWebsiteProjectApplicationTests {

	@Autowired
	private PostsRepository postsRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void test(){
		List<SearchHit<Posts>> searchHitsList = postsRepository.findThreeRelatedPosts("Hành Động");
//		List<SearchHit<Posts>> searchHitsListz = postsRepository.searchByType("ự", "category");
	}

}
