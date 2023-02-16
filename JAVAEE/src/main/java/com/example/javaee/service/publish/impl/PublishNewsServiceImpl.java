package com.example.javaee.service.publish.impl;

import com.example.javaee.dao.*;
import com.example.javaee.entity.Category;
import com.example.javaee.entity.News;
import com.example.javaee.vo.SimpleNews;
import com.example.javaee.service.publish.IPublishNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublishNewsServiceImpl implements IPublishNewsService {
    @Autowired
    private InsertDao insertDao;
    @Autowired
    private SelectDao selectDao;
    @Autowired
    private UpdateDao updateDao;
    @Autowired
    private DeleteDao deleteDao;


    public boolean categoryExist(String categoryName)
    {
        Category category = selectDao.categorySelectByName(categoryName);
        return category != null;
    }

    public boolean newsBelongsToUser(int user_id, int news_id)
    {
        News news = selectDao.getNewsByNewsId(news_id);
        return news.getAuthor_id() == user_id;
    }

    public boolean newsIsEditable(int news_id)
    {
        News news = selectDao.getNewsByNewsId(news_id);
        return news.getState() == 0;
    }



    public void createNews(int user_id, String title, String content, String categoryName)
    {
        java.util.Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        Category category = selectDao.categorySelectByName(categoryName);
        insertDao.insertNews(user_id, title, content, category.getCategory_id(), time, time, 0);
    }

    public List<SimpleNews> getAllSimpleNews(int user_id)
    {
        List<SimpleNews> simpleNews = selectDao.selectAllSimpleNewsByUserId(user_id);
        List<SimpleNews> res = simpleNews.stream()
                .filter(news -> news.getState() == 3)
                .collect(Collectors.toList());
        return res;
    }

    public News getNews(int news_id)
    {
        return selectDao.getNewsByNewsId(news_id);
    }

    public void saveNews(int news_id, String title, String content, String categoryName)
    {
        java.util.Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        Category category = selectDao.categorySelectByName(categoryName);
        updateDao.updateNews(news_id, title, content, category.getCategory_id(), time);
    }


    public void publishNews(int news_id,String title,String content,String category)
    {
        saveNews(news_id, title, content, category);
        updateDao.updateNewsState(news_id, 1);
    }



}
