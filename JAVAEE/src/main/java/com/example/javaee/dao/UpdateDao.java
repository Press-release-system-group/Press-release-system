package com.example.javaee.dao;

import com.example.javaee.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

@Mapper
public interface UpdateDao {

    //这里是用户表的sql

    //这里是新闻表的sql
    //todo 希望统一一下格式
    @Update("UPDATE News SET title = #{title}, content = #{content}, category_id = #{category_id}, update_time = #{update_time} WHERE news_id = #{news_id}")
    int updateNews(int news_id, String title, String content, int category_id, Timestamp update_time);

                 //todo 这里不小心被我删了一条，好像是根据新闻id改变新闻状态



    //这里是评论表的sql
    @Update("UPDATE  Comments SET  state = #{state} WHERE comment_id = #{comment_id}")
    int commentsUpdateByComment_id(int state,int comment_id);

    @Update("UPDATE  Comments SET  state = #{state} WHERE news_id = #{news_id}")
    int commentsUpdateByNews_id(int state,int news_id);

    //这里是喜欢表
    @Update("UPDATE  News_Like SET  state = #{state} WHERE news_id = #{news_id}")
    int likesUpdateByNews_id(int news_id,int state);
    //这里是新闻类别表
    @Update("UPDATE News_Categories SET  state = #{state} WHERE category_id = #{category_id}")
    int CategoryUpdateByCategory_id(int category_id,int state);

    //这里是联合表操作
}
