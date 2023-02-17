package com.example.javaee.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CommentsDetail {
    private int comment_id;
    private String content;
    private int user_id;
    private int news_id;
    private Date create_time;
    private int state;
    private String user_name;
    private String news_title;
}
