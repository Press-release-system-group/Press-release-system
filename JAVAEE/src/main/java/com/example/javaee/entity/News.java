package com.example.javaee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    int news_id;
    String title;
    String content;
    int author_id;
    int category_id;
    Date create_time;
    Date update_time;
    int state;
}
