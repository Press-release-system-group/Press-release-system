package com.example.javaee.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
//这个是新闻详情
public class NewsDetails
{
    int news_id;
    String title;
    String author_name;
    String category_name;
    String content;
    int likes_cnt;
    Date create_time;
    Date update_time;
    int state;
}
