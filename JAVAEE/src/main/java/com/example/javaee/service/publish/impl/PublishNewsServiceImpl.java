package com.example.javaee.service.publish.impl;

import com.example.javaee.dao.*;
import com.example.javaee.entity.Category;
import com.example.javaee.entity.News;
import com.example.javaee.exceptionHandler.exception.BusinessException;
import com.example.javaee.exceptionHandler.exception.ExceptionEnum;
import com.example.javaee.vo.NewsDetails;
import com.example.javaee.vo.SimpleNews;
import com.example.javaee.service.publish.IPublishNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor =RuntimeException.class)
public class PublishNewsServiceImpl implements IPublishNewsService {
    @Autowired
    private InsertDao insertDao;
    @Autowired
    private SelectDao selectDao;
    @Autowired
    private UpdateDao updateDao;
    @Autowired
    private DeleteDao deleteDao;

    @Autowired
    private UserDao userDao;

    public boolean newsExist(int news_id)
    {
        News news = selectDao.newsSelectByNewsId(news_id);
        return news != null && news.getState() != 2;//删除状态的新闻对新闻发布者不可见
    }

    public boolean categoryExist(String categoryName)
    {
        Category category = selectDao.categorySelectByName(categoryName);
        return category != null;
    }

    public boolean newsBelongsToUser(int user_id, int news_id)
    {
        News news = selectDao.newsSelectByNewsId(news_id);
        return news.getAuthor_id() == user_id;
    }

    public boolean newsIsEditable(int news_id)
    {
        News news = selectDao.newsSelectByNewsId(news_id);
        return news.getState() == 0;
    }

    public boolean newsIsDeletable(int news_id)
    {
        News news = selectDao.newsSelectByNewsId(news_id);
        return news.getState() == 0 || news.getState() == 3;
    }


    public void createNews(int user_id, String title, String content, String categoryName)
    {
        java.util.Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        Category category = selectDao.categorySelectByName(categoryName);
        insertDao.newsInsert(user_id, title, content, category.getCategory_id(), time, time, 0);
    }

    public List<SimpleNews> getAllSimpleNews(int user_id)
    {
        List<SimpleNews> simpleNews = selectDao.allSimpleNewsSelectByUserId(user_id);
        List<SimpleNews> res = simpleNews.stream()
                .filter(news -> news.getState() != 3)
                .collect(Collectors.toList());
        return res;
    }

    public NewsDetails getNewsDetail(int news_id)
    {
        News news = selectDao.newsSelectByNewsId(news_id);
        int author_id = news.getAuthor_id();
        int category_id = news.getCategory_id();
        String author_name= userDao.findByUserId(author_id).getName();
        String category_name = selectDao.categorySelectByCategory_id(category_id).getName();

        NewsDetails newsDetails = new NewsDetails();
        newsDetails.setAuthor_id(author_id);
        newsDetails.setAuthor_name(author_name);
        newsDetails.setCategory_id(category_id);
        newsDetails.setCategory_name(category_name);
        newsDetails.setContent(news.getContent());
        newsDetails.setCreate_time(news.getCreate_time());
        newsDetails.setLikes_cnt(selectDao.likesCountByNews_id(news_id));
        newsDetails.setNews_id(news_id);
        newsDetails.setState(news.getState());
        newsDetails.setTitle(news.getTitle());
        newsDetails.setUpdate_time(news.getUpdate_time());

        return newsDetails;
    }

    public void saveNews(int news_id, String title, String content, String categoryName)
    {
        java.util.Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        Category category = selectDao.categorySelectByName(categoryName);
        updateDao.newsUpdateByNews_id(news_id, title, content, category.getCategory_id(), time);
    }


    public void publishNews(int news_id,String title,String content,String category)
    {
        saveNews(news_id, title, content, category);
        updateDao.newsStateUpdateByNews_id(news_id, 1);
    }

    public void deleteNews(int news_id)
    {
        updateDao.newsStateUpdateByNews_id(news_id , 2);
    }

}
