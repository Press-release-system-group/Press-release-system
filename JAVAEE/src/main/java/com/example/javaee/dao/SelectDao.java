package com.example.javaee.dao;

import com.example.javaee.entity.Category;
import com.example.javaee.entity.Comments;
import com.example.javaee.entity.Likes;
import com.example.javaee.entity.News;
import com.example.javaee.vo.CommentsDetail;
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

    @Select("SELECT * FROM News WHERE state = #{state}")
    List<News>  newsSelectByState(int state);

    @Select("SELECT * FROM News WHERE author_id = #{author_id}")
    List<News> newsSelectByAuthor_id(int author_id);

    @Select("SELECT * FROM News WHERE category_id = #{category_id}")
    List<News> newsSelectByCategory_id(int category_id);


    @Select("SELECT * FROM News ")
    List<News> newsSelectAll();

    //这里是评论表的sql

    @Select("SELECT * FROM Comments WHERE user_id = #{user_id}")
    List<Comments> commentsSelectByUser_id(int user_id);

    @Select("SELECT * FROM Comments WHERE news_id = #{news_id}")
    List<Comments> commentsSelectByNews_id(int news_id);

    @Select("SELECT * FROM Comments WHERE state = #{state}")
    List<Comments> commentsSelectByState(int state);

    @Select("SELECT * FROM Comments WHERE comment_id = #{comment_id}")
    Comments commentsSelectByComment_id(int comment_id);



    //这里是喜欢表

    @Select("SELECT count(*) FROM News_Like WHERE news_id = #{news_id}")
    int likesCountByNews_id(int news_id);

    @Select("SELECT * FROM News_Like WHERE user_id = #{user_id}")
    List<Likes> likesSelectByUser_id(int user_id);

    @Select("SELECT * FROM News_Like WHERE news_id = #{news_id}")
    List<Likes> likesSelectByNews_id(int news_id);

    @Select("SELECT * FROM News_Like WHERE state = #{state}")
    List<Likes> likesSelectByState(int state);
    //这里是新闻类别表
    @Select("SELECT * FROM News_Categories WHERE name = #{categoryName}")
    Category categorySelectByName(String categoryName);

    @Select("SELECT * FROM News_Categories WHERE category_id = #{category_id}")
    Category categorySelectById(int category_id);

    @Select("SELECT * FROM News_Categories")
    List<Category> categorySelectAll();

    @Select("SELECT * FROM News_Categories WHERE category_id = #{category_id}")
    Category categorySelectByCategory_id(int category_id);

    //这里是联合表操作
    @Select("SELECT n.news_id,n.title,c.name,n.create_time,n.update_time,n.state FROM News n " +
            "LEFT JOIN News_Categories c ON n.category_id = c.category_id " +
            "WHERE author_id = #{user_id} ORDER BY update_time")
    List<SimpleNews> allSimpleNewsSelectByUserId(int user_id);
}
