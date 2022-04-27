package com.example.BlogMovieWebsiteProject;

import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.*;

@SpringBootTest
class BlogMovieWebsiteProjectApplicationTests {

	@Autowired
	private PostsRepository postsRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testListRecentPost(){
		List<Posts> postsList1 = postsRepository.findAllByBrowserOrderByCreatedDesc(false);
	}

}
