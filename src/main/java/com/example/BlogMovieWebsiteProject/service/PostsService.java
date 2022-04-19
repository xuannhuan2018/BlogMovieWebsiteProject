package com.example.BlogMovieWebsiteProject.service;

import com.example.BlogMovieWebsiteProject.dto.ItemPostsDto;
import com.example.BlogMovieWebsiteProject.dto.PostsDto;
import com.example.BlogMovieWebsiteProject.model.ItemPosts;
import com.example.BlogMovieWebsiteProject.model.ItemType;
import com.example.BlogMovieWebsiteProject.model.Posts;
import com.example.BlogMovieWebsiteProject.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.BlogMovieWebsiteProject.repository.PostsRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PostsService
{
    private final UsersRepository usersRepository;

    private final PostsRepository postsRepository;

    private final FileUploadService fileUploadService;
    //private final ConvertService convertService;

    public long createPosts (PostsDto postsDto, String username) throws IOException {
        Posts posts = new Posts();

        posts.setUsername(username);
        posts.setCategory(postsDto.getCategory());
        posts.setTitle(postsDto.getTitle());
        posts.setDescription(postsDto.getDescription());
        posts.setIMDb(postsDto.getIMDb());
        posts.setYourRating(postsDto.getYourRating());
        //posts.setCreated(convertService.DateTimeToString());
        posts.setBrowser(false);

        if( postsRepository.findTopByOrderByNumberDesc()==null){
            posts.setNumber(0);
        }
        else {
            posts.setNumber(postsRepository.findTopByOrderByNumberDesc().getNumber() + 1);
        }

        String fileImgHeader=StringUtils.cleanPath(postsDto.getImgHeader().getOriginalFilename());
        if(!fileImgHeader.equals("")) {
            posts.setImgHeader("ImagesManager/ImgPosts/"+posts.getNumber() + "/" + fileImgHeader);
            fileUploadService.saveFile("ImagesManager/ImgPosts/" + posts.getNumber(), fileImgHeader, postsDto.getImgHeader());
        }

        if(postsDto.getItemPostsDto()!=null)
        {
            List<ItemPosts> items= new ArrayList<ItemPosts>();
            int index=0;
            for (ItemPostsDto item: postsDto.getItemPostsDto())
            {
                ItemPosts itemPosts=new ItemPosts();
                itemPosts.setNumber(index);
                itemPosts.setType(ItemType.IMG);
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

            posts.setItemPosts(items);
        }
        postsRepository.save(posts);
        return posts.getNumber();
    }
}
