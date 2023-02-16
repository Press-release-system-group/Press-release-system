package com.example.javaee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Likes
{
    int like_id;
    int user_id;
    int news_id;
    Date create_time;
    int state;
}
