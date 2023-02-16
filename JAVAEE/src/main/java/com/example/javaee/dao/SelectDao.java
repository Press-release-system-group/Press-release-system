package com.example.javaee.dao;

import com.example.javaee.entity.Category;
import com.example.javaee.entity.News;
import com.example.javaee.vo.SimpleNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SelectDao {

    //这里是用户表的sql

    //这里是新闻表的sql
    @Select("SELECT * FROM News WHERE news_id = #{news_id}")
    News newsSelectByNewsId(int news_id);

    List<SimpleNews> newsSelectByStatus(List<Integer>  list);

    @Select("SELECT * FROM News WHERE state = #{id}")
    News  newsSelectByState(int state);

    @Select("SELECT * FROM News WHERE author_id = #{id}")
    News newsSelectByAuthor_id(int author_id);

    @Select("SELECT * FROM News WHERE category_id = #{id}")
    News newsSelectByCategory_id(int category_id);




    //这里是评论表的sql

    //这里是喜欢表

    //这里是新闻类别表
    @Select("SELECT * FROM News_Categories WHERE name = #{categoryName}")
    Category categorySelectByName(String categoryName);

    //这里是联合表操作
    @Select("SELECT n.news_id,n.title,c.name,n.create_time,n.update_time,n.state FROM News n " +
            "LEFT JOIN News_Categories c ON n.category_id = c.category_id " +
            "WHERE author_id = #{user_id} ORDER BY update_time")
    List<SimpleNews> allSimpleNewsSelectByUserId(int user_id);
}
