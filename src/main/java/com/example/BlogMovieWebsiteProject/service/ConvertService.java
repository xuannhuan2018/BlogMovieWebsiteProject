package com.example.BlogMovieWebsiteProject.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class ConvertService
{
    public String DateTimeToString()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.CHINA);
        Date date = new Date();
        return formatter.format(date);

    }
}
