package com.example.javaee.dao;

import com.example.javaee.utils.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface InsertDao {

    //这里是用户表的sql

    //这里是新闻表的sql
    @Insert("INSERT INTO News (title,content,author_id,category_id,create_time,update_time,state) VALUES" +
            " (#{title},#{content},#{user_id},#{category_id},#{create_time},#{update_time},#{state})")
    int insertNews(int user_id, String title, String content, int category_id, Timestamp create_time, Timestamp update_time, int state);

    //这里是评论表的sql

    //这里是喜欢表

    //这里是新闻类别表

    //这里是联合表操作
}
