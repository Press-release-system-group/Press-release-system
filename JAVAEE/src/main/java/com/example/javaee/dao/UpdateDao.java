package com.example.javaee.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

@Mapper
public interface UpdateDao {

    //这里是用户表的sql

    //这里是新闻表的sql
    @Update("UPDATE News SET title = #{title}, content = #{content}, category_id = #{category_id}, update_time = #{update_time} WHERE news_id = #{news_id}")
    int updateNews(int news_id, String title, String content, int category_id, Timestamp update_time);

    @Update("UPDATE News SET state = #{state} WHERE news_id = #{news_id}")
    int updateNewsState(int news_id, int state);

    //这里是评论表的sql

    //这里是喜欢表

    //这里是新闻类别表


    //这里是联合表操作
}
