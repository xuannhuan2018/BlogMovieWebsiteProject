package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.dto.ItemPostsDto;
import com.example.BlogMovieWebsiteProject.dto.PostDetailDto;
import com.example.BlogMovieWebsiteProject.dto.PostsDto;
import com.example.BlogMovieWebsiteProject.model.ItemPosts;
import com.example.BlogMovieWebsiteProject.model.ItemType;
import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostsService {
    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final PostsRepository postsRepository;

    @Autowired
    private final FileUploadService fileUploadService;

    public String createPosts (PostsDto postsDto, String username) throws IOException {
        Posts posts = new Posts();
        Date date = new Date();
        posts.setUsername(username);
        posts.setCategory(postsDto.getCategory());
        posts.setTitle(postsDto.getTitle());
        posts.setDescription(postsDto.getDescription());
        posts.setIMDb(postsDto.getIMDb());
        posts.setYourRating(postsDto.getYourRating());
        posts.setCreated(date);
        posts.setBrowser(false);

        List<Posts> postsList = postsRepository.findAll();
        if(postsList.size() == 0){
            posts.setNumber(0);
        }
        else {
            posts.setNumber(postsList.size() + 1);
        }
        String fileImgHeader=StringUtils.cleanPath(postsDto.getImgHeader().getOriginalFilename());
        if(!fileImgHeader.equals("")) {
            posts.setImgHeader("ImagesManager/ImgPosts/"+posts.getNumber() + "/" + fileImgHeader);
            fileUploadService.saveFile("ImagesManager/ImgPosts/" + posts.getNumber(), fileImgHeader, postsDto.getImgHeader());
        }

        if(postsDto.getItemPost()!=null)
        {
            List<ItemPosts> items= new ArrayList<>();
            int index=0;
            for (ItemPostsDto item: postsDto.getItemPost())
            {
                ItemPosts itemPosts=new ItemPosts();
                itemPosts.setNumber(index);
                itemPosts.setType(item.getType());
                itemPosts.setText(item.getText());
                if(item.getType()== ItemType.IMG){
                    String filename=StringUtils.cleanPath(item.getImg().getOriginalFilename());
                    if(!filename.equals("")) {
                        itemPosts.setText("ImagesManager/ImgPosts/"+posts.getNumber() + "/" + filename);
                        fileUploadService.saveFile("ImagesManager/ImgPosts/" + posts.getNumber(), filename, item.getImg());
                        items.add(itemPosts);
                        index++;
                    }
                } else {
                    items.add(itemPosts);
                    index++;
                }
            }

            posts.setItemPost(items);
        }
        postsRepository.save(posts);
        return posts.getId();
    }

    public List<PostDetailDto> listPostByUsername(String username){
        List<Posts> postsList = postsRepository.findAllByUsername(username);
        List<PostDetailDto> postDtoList = new ArrayList<>();
        for(Posts posts:postsList) {
            PostDetailDto postDto = this.setPostModelToPostDto(posts);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }

    public PostDetailDto viewDetailPost(String postId){
        Optional<Posts> opPosts = postsRepository.findById(postId);
        Posts posts = opPosts.get();
        PostDetailDto postDto = this.setPostModelToPostDto(posts);
        return postDto;
    }
    public PostDetailDto setPostModelToPostDto(Posts posts){
        PostDetailDto postDetailDto = new PostDetailDto();
        postDetailDto.setId(posts.getId());
        postDetailDto.setUsername(posts.getUsername());
        postDetailDto.setCategory(posts.getCategory());
        postDetailDto.setTitle(posts.getTitle());
        postDetailDto.setImgHeader(posts.getImgHeader());
        postDetailDto.setDescription(posts.getDescription());
        postDetailDto.setItemPost(posts.getItemPost());
        postDetailDto.setIMDb(posts.getNumber());
        postDetailDto.setYourRating(posts.getYourRating());
        postDetailDto.setCreated(posts.getCreated());
        postDetailDto.setBrowser(posts.isBrowser());
        return postDetailDto;
    }
}
