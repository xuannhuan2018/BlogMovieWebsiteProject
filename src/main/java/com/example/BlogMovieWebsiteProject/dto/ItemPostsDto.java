package com.example.BlogMovieWebsiteProject.dto;

import com.example.BlogMovieWebsiteProject.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPostsDto
{
    private String text;
    private MultipartFile img;
    private ItemType type;
    private int number;
}
