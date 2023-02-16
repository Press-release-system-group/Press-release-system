package com.example.javaee.service.admin;

import com.example.javaee.entity.News;
import com.example.javaee.vo.NewsDetails;
import com.example.javaee.vo.SimpleNews;

import java.util.List;

public interface IAdminNewsService {

    //寻找简略新闻集合
    List<SimpleNews> getSimpleNews(List<Integer> list);

    //查找某个详细新闻
    NewsDetails getNewsById(int id);

    //审核某个新闻
    boolean changeNewsState(int news_id,int state);


    //删除某个新闻类别，并且删除它所有新闻以及新闻评论以及点赞;
    boolean changeCategoryState(int Category_id);
}
