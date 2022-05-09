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
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;

import javax.servlet.http.HttpServletRequest;
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
        posts.setTags(postsDto.getTags());
        posts.setYourRating(postsDto.getYourRating());
        posts.setCreated(date);
        posts.setVisit(0);
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

    public PostDetailDto viewDetailPost(String postId, HttpServletRequest request){
        Optional<Posts> opPosts = postsRepository.findById(postId);
        Posts posts = opPosts.get();
        if (!request.getRequestURL().toString().contains("/user/post")){
            posts.setVisit(posts.getVisit()+1);
            postsRepository.save(posts);
        }
        PostDetailDto postDto = this.setPostModelToPostDto(posts);
        return postDto;
    }

    public List<PostDetailDto> search(String keyword, String searchType){
        List<SearchHit<Posts>> searchHitList = null;
        List<PostDetailDto> postDetailDtoList = new ArrayList<>();
        if(searchType.equals("all")){
            searchHitList = postsRepository.searchAll(keyword);
        }
        else {
            searchHitList = postsRepository.searchByType(keyword, searchType);
        }
        if(searchHitList != null){
            for (SearchHit<Posts> postsSearchHit : searchHitList) {
                PostDetailDto postDetailDto;
                Posts posts = postsSearchHit.getContent();
                postDetailDto = this.setPostModelToPostDto(posts);
                postDetailDtoList.add(postDetailDto);
            }
        }
        return postDetailDtoList;
    }
    public List<PostDetailDto> listAllPost(){
        List<PostDetailDto> postDetailDtoList = new ArrayList<>();
        List<SearchHit<Posts>> postsSearchHitList = postsRepository.findAllPost();
        for (SearchHit<Posts> postsSearchHit : postsSearchHitList) {
            PostDetailDto postDetailDto;
            Posts posts = postsSearchHit.getContent();
            postDetailDto = this.setPostModelToPostDto(posts);
            postDetailDtoList.add(postDetailDto);
        }
        return postDetailDtoList;
    }

    //Get the 3 posts with the highest number of views (visit)
    public List<PostDetailDto> listTopThreePostsHighestViews(){
        List<PostDetailDto> postDetailDtoLists = new ArrayList<>();
        List<SearchHit<Posts>> postsSearchHitList = postsRepository.findThreePostsMaxViews();
        for (SearchHit<Posts> postsSearchHit : postsSearchHitList) {
            Posts posts = postsSearchHit.getContent();
            PostDetailDto postDetailDto = new PostDetailDto();
            postDetailDto.setId(posts.getId());
            postDetailDto.setTitle(posts.getTitle());
            postDetailDto.setUsername(posts.getUsername());
            postDetailDto.setCreated(posts.getCreated());
            postDetailDto.setDescription(posts.getDescription());
            postDetailDto.setCategory(posts.getCategory());
            postDetailDto.setImgHeader(posts.getImgHeader());
            postDetailDtoLists.add(postDetailDto);
        }
        return postDetailDtoLists;
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
        postDetailDto.setTags(posts.getTags());
        postDetailDto.setYourRating(posts.getYourRating());
        postDetailDto.setCreated(posts.getCreated());
        postDetailDto.setVisit(posts.getVisit());
        postDetailDto.setBrowser(posts.isBrowser());
        return postDetailDto;
    }

}
