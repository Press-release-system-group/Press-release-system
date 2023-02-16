package com.example.javaee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    private int comment_id;
    private String content;
    private int user_id;
    private int news_id;
    private Date create_time;
    private int state;
}
