package com.example.javaee.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//这个是简略新闻
public class SimpleNews
{
    int news_id;
    String title;
    String category;
    Date create_time;
    Date update_time;
    int state;
}
