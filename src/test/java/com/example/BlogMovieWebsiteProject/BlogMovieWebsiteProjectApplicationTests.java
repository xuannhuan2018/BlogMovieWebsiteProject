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
	public void testListRecentPost(){
//		List<Posts> postsList1 = postsRepository.findAllByBrowserOrderByCreatedDesc(false);
		//PostRepositoryCustom postsRepository = new PostsRepository_impl();
		List<SearchHit<Posts>> searchHitsList = postsRepository.findListPostInHome();
	}

}
