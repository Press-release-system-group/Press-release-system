package com.example.javaee.dao;

import com.example.javaee.entity.Category;
import com.example.javaee.entity.Comments;
import com.example.javaee.entity.Likes;
import com.example.javaee.utils.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface InsertDao {

    //这里是用户表的sql

    //这里是新闻表的sql

    //todo 希望统一一下格式
    @Insert("INSERT INTO News (title,content,author_id,category_id,create_time,update_time,state) VALUES" +
            " (#{title},#{content},#{user_id},#{category_id},#{create_time},#{update_time},#{state})")
    int insertNews(int user_id, String title, String content, int category_id, Timestamp create_time, Timestamp update_time, int state);



    //这里是评论表的sql
    @Insert("INSERT INTO Comments (content,user_id,news_id,create_time,state) VALUES" +
            " (#{content},#{user_id},#{news_id},#{create_time},#{state})")
    int commentsInsert(Comments comments);


    //这里是喜欢表
    @Insert("INSERT INTO News_Like (user_id,news_id,create_time,state) VALUES" +
            " (#{user_id},#{news_id},#{create_time},#{state})")
    int likesInsert(Likes likes);



    //这里是新闻类别表
    @Insert("INSERT INTO News_Categories (name,create_time,state) VALUES" +
            " (#{name},#{create_time},#{state})")
    int categoryInsert(Category category);


    //这里是联合表操作
}
