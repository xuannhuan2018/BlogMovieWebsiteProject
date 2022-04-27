package com.example.BlogMovieWebsiteProject.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    public List<String> listCategory(){
        List<String> listCategory = new ArrayList<>();
        listCategory.add("Hình Sự");
        listCategory.add("Thể Thao");
        listCategory.add("Võ Thuật - Kiếm Hiệp");
        listCategory.add("Hoạt Hình");
        listCategory.add("Hài Hước");
        listCategory.add("Tâm Lý - Tình Cảm");
        listCategory.add("Cổ Trang");
        listCategory.add("Hành Động");
        listCategory.add("Ma - Kinh Dị");
        listCategory.add("Khoa học - Viễn Tưởng");
        listCategory.add("Tài Liệu");
        return listCategory;
    }
}
